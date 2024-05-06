package org.dsi.com.approvalService.service.implementation;

import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.QuestionApprovalDto;
import org.dsi.com.approvalService.dto.response.QuestionResponseDto;
import org.dsi.com.approvalService.dto.response.RoleResponseDto;
import org.dsi.com.approvalService.dto.response.UserResponseDto;
import org.dsi.com.approvalService.model.ApprovalStep;
import org.dsi.com.approvalService.model.ApproverGroup;
import org.dsi.com.approvalService.model.QuestionApprovals;
import org.dsi.com.approvalService.repository.QuestionApprovalRepository;
import org.dsi.com.approvalService.service.*;
import org.dsi.com.approvalService.utils.ApprovalStatus;
import org.dsi.com.approvalService.utils.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuestionApprovalServiceImpl implements QuestionApprovalService {
    final QuestionApprovalRepository questionApprovalRepository;
    final ApprovalServiceImpl approvalService;
    final ApproverGroupService approverGroupService;
    final UserService userService;
    final ApprovalStepService approvalStepService;
    final QuestionService questionService;
    final CategoryApprovalProcessService categoryApprovalProcessService;

    public QuestionApprovalServiceImpl(QuestionApprovalRepository questionApprovalRepository,
                                       ApprovalServiceImpl approvalService,
                                       ApproverGroupService approverGroupService,
                                       UserService userService,
                                       ApprovalStepService approvalStepService,
                                       QuestionService questionService,
                                       CategoryApprovalProcessService categoryApprovalProcessService) {
        this.questionApprovalRepository = questionApprovalRepository;
        this.approvalService = approvalService;
        this.approverGroupService = approverGroupService;
        this.userService = userService;
        this.approvalStepService = approvalStepService;
        this.questionService = questionService;
        this.categoryApprovalProcessService = categoryApprovalProcessService;
    }

    /**
     * This method is used to find all QuestionApprovals by a specific question ID.
     *
     * @param questionID The ID of the question for which the approvals are to be found.
     * @return A list of QuestionApprovals associated with the given question ID.
     */
    @Override
    public List<QuestionApprovals> findByQuestionId(Long questionID) {
        return questionApprovalRepository.findQuestionApprovalsByQuestionId(questionID);
    }

    /**
     * @param questionID question id
     * @param questionApprovalDto payload for question approval
     * @return new QuestionApprovals
     * @implSpec create approval for question, check if the user is authorized to approve the question
     */
    @Override
    @Transactional
    public Optional<QuestionApprovals> createApprovalForQuestion(Long questionID,
                                                                 QuestionApprovalDto questionApprovalDto) throws Exception {

        //check if it's a valid group

        Optional<ApproverGroup> approverGroupOfPayloadOptional = validateApproverGroup(questionApprovalDto);

        // check if the group has permission to approve
        List<ApproverGroup> approverGroupsWithPermission = validateApproverGroupPermission(questionApprovalDto,
                approverGroupOfPayloadOptional);
        //get the user
        UserResponseDto userResponseDto;
        try {
            userResponseDto = userService.getUserById(questionApprovalDto.getApproverUserId());
        } catch (Exception e) {
            log.error("Error while fetching user details", e);
            throw new BadRequestException("Error while fetching user details");
        }

        //check if the user has permission to approve
        Boolean ifUserRoleHasPermission = validateUserPermission(userResponseDto, approverGroupsWithPermission);
        if (!ifUserRoleHasPermission) {
            log.error("User does not have permission to approve");
            throw new BadRequestException("User does not have permission to approve");
        }
        QuestionApprovals.QuestionApprovalsBuilder builder = QuestionApprovals.builder()
                .questionId(questionID)
                .approverGroupId(questionApprovalDto.getApproverGroupId())
                .approverUserId(questionApprovalDto.getApproverUserId())
                .approvalStepId(questionApprovalDto.getApprovalStepId())
                .statusCode(ApprovalStatus.getCode(questionApprovalDto.getStatusCode()))
                .approvedDate(new Date());
        QuestionApprovals questionApproval = questionApprovalRepository.save(builder.build());

        //check if this is the last step and update the question status
        if (ApprovalStatus.REJECTED.getCode() == questionApproval.getStatusCode()) {
            log.info("Question is rejected updating question status");
            approvalService.updateQuestionStatus(questionApproval);

        }

        Boolean isLastStep = checkIfThisIsTheLastStep(questionApproval);
        if (isLastStep || (questionApproval.getStatusCode()  == ApprovalStatus.REJECTED.getCode())) {
            approvalService.updateQuestionStatus(questionApproval);
        }else{
            sendNotificationToNextApprover(questionApproval);
        }

        return Optional.of(questionApproval);
    }

    private void sendNotificationToNextApprover(QuestionApprovals questionApproval) {
        //TODO:// send notification to next approver
    }

    /**
     * This method checks if the current approval step is the last step in the approval process.
     *
     * @param questionApprovals The QuestionApprovals object containing the details of the current approval step.
     * @return A Boolean value indicating whether the current approval step is the last step in the approval process.
     */
    private Boolean checkIfThisIsTheLastStep(QuestionApprovals questionApprovals) {
        Optional<QuestionResponseDto> questionResponseDtoOptional= questionService.findById(questionApprovals.getQuestionId());
        if(questionResponseDtoOptional.isEmpty()){
            log.error("Question not found can not check last step");
            return false;
        }
        Long categoryId = questionResponseDtoOptional.get().getCategoryID();

        List<ApprovalStep> steps= approvalStepService.findByCategoryIdAndActivateStatus(categoryId, Status.ACTIVE.getDescription());
        if (steps.isEmpty()) {
            log.error("No active approval steps found for category id: {}", categoryId);
            return false;
        }
        if( (steps.get(steps.size()-1)).getId() == questionApprovals.getApprovalStepId()){
            log.info("this is the last step {}", questionApprovals.getApprovalStepId());
            return true;
        }

        return false;

    }
    /**
     * This method checks if the user has the necessary permissions to approve.
     *
     * @param userResponseDto The UserResponseDto object containing the details of the user.
     * @param approverGroupsWithPermission The list of ApproverGroup objects that have permission to approve.
     * @return A Boolean value indicating whether the user has the necessary permissions to approve.
     */
    private Boolean validateUserPermission(UserResponseDto userResponseDto,
                                           List<ApproverGroup> approverGroupsWithPermission) {

        List<Long> usersRoleList = userResponseDto
                        .getRoles()
                        .stream()
                        .map(RoleResponseDto::getRoleId)
                        .toList();
        log.info("user roles: {}", usersRoleList);
        log.info(" grup {}" ,approverGroupsWithPermission.stream().toList());
        return approverGroupsWithPermission
                .stream()
                .anyMatch(approverGroup -> usersRoleList.contains(approverGroup.getRolesId()));
    }
    /**
     * This method validates if the approver group has the permission to approve the current step.
     *
     * @param questionApprovalDto The QuestionApprovalDto object containing the details of the approval.
     * @param approverGroupOfPayloadOptional The Optional<ApproverGroup> object containing the approver group details.
     * @return A list of ApproverGroup objects that have permission to approve the current step.
     * @throws BadRequestException if no approver group is available to approve the current step or if the approver group in the request does not have permission to approve.
     */
    private List<ApproverGroup> validateApproverGroupPermission(QuestionApprovalDto questionApprovalDto,
                                                                Optional<ApproverGroup> approverGroupOfPayloadOptional)
    {
        log.info("here");
        List<ApproverGroup> approverGroupListWithPermissionToApprove =
                approverGroupService.getAllApproverGroupsByStepId(questionApprovalDto.getApprovalStepId());

        if (approverGroupListWithPermissionToApprove.isEmpty()) {
            log.error("NO approver group available to approve this step {} ",
                    questionApprovalDto.getApprovalStepId());
            throw new BadRequestException("NO approver group available to approve this step");
        }

        boolean approverGroupHasPermission =
                approverGroupListWithPermissionToApprove.stream()
                        .anyMatch(approverGroup -> approverGroup.getId()
                                .equals(approverGroupOfPayloadOptional.get().getId()));

        if (!approverGroupHasPermission) {
            log.error( "Approver Group in request does not have permission to approve");
            throw new BadRequestException("Approver Group in request does not have permission to approve");
        }
        return  approverGroupListWithPermissionToApprove;
    }
    /**
     * This method validates the ApproverGroup based on the provided QuestionApprovalDto.
     * It checks if the ApproverGroup, identified by the ApproverGroupId in the QuestionApprovalDto, exists and is not deleted.
     *
     * @param questionApprovalDto The QuestionApprovalDto object containing the details of the approval.
     * @return An Optional<ApproverGroup> object. If the ApproverGroup exists and is not deleted, it is returned.
     *         If the ApproverGroup does not exist or is deleted, an empty Optional is returned.
     * @throws BadRequestException if the ApproverGroup does not exist or is deleted.
    */

    private Optional<ApproverGroup> validateApproverGroup(QuestionApprovalDto questionApprovalDto) throws Exception {
        Optional<ApproverGroup> approverGroupInRequestOptional
                = approverGroupService.getByIdAndNotDeleted(questionApprovalDto.getApproverGroupId());

        if (approverGroupInRequestOptional.isEmpty()) {
            log.error("Approver Group not found");
            throw new BadRequestException("Approver Group not found");
        }
        return approverGroupInRequestOptional;
    }


}
