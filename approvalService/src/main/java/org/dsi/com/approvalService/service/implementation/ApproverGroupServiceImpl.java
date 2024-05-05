package org.dsi.com.approvalService.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.ApproverGroupDto;
import org.dsi.com.approvalService.model.ApproverGroup;
import org.dsi.com.approvalService.repository.ApproverGroupRepository;
import org.dsi.com.approvalService.service.ApproverGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApproverGroupServiceImpl implements ApproverGroupService {

   final ApproverGroupRepository approverGroupRepository;

    public ApproverGroupServiceImpl(ApproverGroupRepository approverGroupRepository) {
        this.approverGroupRepository = approverGroupRepository;
    }

    /**
     * @param stepId
     * @return
     */
    @Override
    public List<ApproverGroup> getAllApproverGroupsByStepId(Long stepId) {
        return approverGroupRepository.findAllByApprovalStepIdAndIsDeleted(stepId,false);
    }

    /**
     * @param approverGroupDto
     * @implSpec checks if approver group already existed with the same approval step id and roles id and status deleted
     * if the approver group is found then update the status to not deleted
     * else create a new approver group
     * @return approver group
     */
    @Override
    public Optional<ApproverGroup> createApproverGroup(ApproverGroupDto approverGroupDto) {
        ApproverGroup approverGroup = approverGroupRepository
                .findByApprovalStepIdAndRolesIdAndIsDeleted(approverGroupDto.getApprovalStepId(),
                        approverGroupDto.getRolesId(), true)
                .orElseGet(() -> ApproverGroup.builder()
                        .approvalStepId(approverGroupDto.getApprovalStepId())
                        .rolesId(approverGroupDto.getRolesId())
                        .build());
        approverGroup.setIsDeleted(Boolean.FALSE);
        return Optional.of(approverGroupRepository.save(approverGroup));
    }

    @Override
    public void deleteApproverGroup(Long approverGroupId) {
        Optional<ApproverGroup> approverGroupOptional =
                approverGroupRepository.findById(approverGroupId);
        ApproverGroup approverGroup = approverGroupOptional.orElseThrow(() -> new RuntimeException("ApproverGroup not found"));
        approverGroup.setIsDeleted(Boolean.TRUE);
        approverGroupRepository.save(approverGroup);

    }

    /**
    * Retrieves an ApproverGroup by its ID.
    *
    * @param approverGroupId the ID of the ApproverGroup to retrieve
    * @return an Optional containing the ApproverGroup if found, or an empty Optional if not found
    */
    @Override
    public Optional<ApproverGroup> getByIdAndNotDeleted (Long approverGroupId) {
        return approverGroupRepository.findByIdAndIsDeleted(approverGroupId, false);
}
}
