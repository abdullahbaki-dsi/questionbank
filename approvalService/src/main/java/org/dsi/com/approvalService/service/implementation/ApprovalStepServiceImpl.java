package org.dsi.com.approvalService.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.dsi.com.approvalService.dto.ApprovalStepDto;
import org.dsi.com.approvalService.model.ApprovalStep;
import org.dsi.com.approvalService.model.CategoryApprovalProcess;
import org.dsi.com.approvalService.repository.ApprovalStepRepository;
import org.dsi.com.approvalService.service.ApprovalStepService;
import org.dsi.com.approvalService.service.CategoryApprovalProcessService;
import org.dsi.com.approvalService.utils.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



@Service
@Slf4j
public class ApprovalStepServiceImpl implements ApprovalStepService {
    final ApprovalStepRepository approvalStepRepository;
    final CategoryApprovalProcessService categoryApprovalProcessService;

    public ApprovalStepServiceImpl(ApprovalStepRepository approvalStepRepository, 
                                   CategoryApprovalProcessService categoryApprovalProcessService) {
        this.approvalStepRepository = approvalStepRepository;
        this.categoryApprovalProcessService = categoryApprovalProcessService;
    }


    /**
     * @exception BadRequestException
     * @param approvalStepDtos for creating approval step
     * VALIDATIONS: if all items have an approval process ID and serial
     *               if all items have the same approval process ID
     *               if all items have unique serialId, started from 1 and sequential
     *               if the approval process is in draft status
     *               if the approval process is not in active or inactive status(can not edit active or inactive approval process)
     *  @implSpec delete existing approval steps for  ApprovalProcess in DRAFT status
     *  create new approval steps
     * @return newly created approval step
     */

    @Override
    @Transactional
    public List<ApprovalStep> save(List<ApprovalStepDto> approvalStepDtos) throws BadRequestException {
        // Check if all items have an approval process ID and serial
        boolean allItemsHaveApprovalProcessIdAndSerial = approvalStepDtos.stream()
                .allMatch(approvalStepDto -> approvalStepDto.getApprovalProcessId() != null && approvalStepDto.getSerial() != 0);

        if (!allItemsHaveApprovalProcessIdAndSerial)
            throw new BadRequestException("Not all items have an approval process ID and serial");

        // Check if all items have the same approval process ID
        long distinctApprovalProcessIds = approvalStepDtos.stream()
                .map(ApprovalStepDto::getApprovalProcessId)
                .distinct()
                .count();

        //check for duplicate serial
        if (distinctApprovalProcessIds > 1)
            throw new BadRequestException("Not all items have the same approval process ID");


        // Check if all items have the unique serial
        long distinctSerialsCount = approvalStepDtos.stream()
                .map(ApprovalStepDto::getSerial)
                .distinct()
                .count();
        //check for duplicate serial
        if (distinctSerialsCount < approvalStepDtos.size())
            throw new BadRequestException("Duplicate serials found");

        //check if serials are sequential
        approvalStepDtos.sort(Comparator.comparing(ApprovalStepDto::getSerial));
        boolean isSequential = IntStream.rangeClosed(1, approvalStepDtos.size())
                .allMatch(i -> i == approvalStepDtos.get(i - 1).getSerial());

        if (!isSequential) throw new BadRequestException("Serials are not sequential");
        //check the status of category_approval
        List<CategoryApprovalProcess>  categoryApprovalProcessList =
                categoryApprovalProcessService.getCategoryApprovalProcessByApprovalProcessId(
                        approvalStepDtos.get(0).getApprovalProcessId());

        boolean anyActiveOrInactive = categoryApprovalProcessList.stream()
                .anyMatch(process -> Status.ACTIVE.getCode().equalsIgnoreCase(process.getActivateStatus()) ||
                        Status.INACTIVE.getCode().equalsIgnoreCase(process.getActivateStatus()));

        if (anyActiveOrInactive) throw new BadRequestException("Can not edit active or inactive approval process");
        //delete existing approval steps for draft status
       List<ApprovalStep> listOfStepsToDelete=
               approvalStepRepository.findAllByApprovalProcessId(
                       categoryApprovalProcessList.get(0).getApprovalProcessId());
        approvalStepRepository.deleteAll(listOfStepsToDelete);
        log.info("deleted existing steps for approval process id: {}",
                categoryApprovalProcessList.get(0).getApprovalProcessId());
       List<ApprovalStep> approvalSteps = new ArrayList<>();
       for (ApprovalStepDto approvalStepDto : approvalStepDtos) {
           ApprovalStep.ApprovalStepBuilder builder = ApprovalStep.builder();
           builder.approvalProcessId(approvalStepDto.getApprovalProcessId());
           builder.stepName(approvalStepDto.getStepName());
           builder.nextStep(approvalStepDto.getNextStep());
           builder.previousStep(approvalStepDto.getPreviousStep());
           builder.serial(approvalStepDto.getSerial());
           builder.isDeleted(Boolean.FALSE);
           ApprovalStep approvalStep = builder.build();
           approvalSteps.add(approvalStep);
       }

        return approvalStepRepository.saveAll(approvalSteps);
    }

    /**
     * @param approvalProcessId from categoryProcess to find the approval steps
     * @return retunrs sorted by serial setps
     */
    @Override
    public List<ApprovalStep> getApprovalSetpByApprovalProcessId(Long approvalProcessId) {
        return approvalStepRepository.findApprovalStepsByApprovalProcessIdOrderBySerial(approvalProcessId)
                .stream()
                .toList();
    }

    /**
     * @param approvalStepId 
     * @return
     */
    @Override
    public Optional<ApprovalStep> getApprovalStepById(Long approvalStepId) {
        return approvalStepRepository.findById(approvalStepId);
    }

    /**
     * @return
     */
    @Override
    public List<ApprovalStep> findAll() {
        return approvalStepRepository.findAll();
    }

    /**
     * @param categoryId 
     * @return
     */
    @Override
    public List<ApprovalStep> findByCategoryId(Long categoryId) {
        //CategoryApprovalProcess categoryApprovalProcess = categoryApprovalProcessService.findByCategoryId(categoryId);

        return new ArrayList<>();
    }

    /**
     * @param categoryId 
     * @param status
     * @return approval steps by category id and status sorted by serial
     */
    @Override
    public List<ApprovalStep> findByCategoryIdAndActivateStatus(Long categoryId, String status) {
        List<CategoryApprovalProcess> listOfApprovalProcess =
                categoryApprovalProcessService.findCategoryApprovalProcessesByCategoryIdAndActivateStatus(categoryId, status);

        return  approvalStepRepository.findApprovalStepsByApprovalProcessIdInOrderBySerial(
                listOfApprovalProcess.stream()
                        .map(CategoryApprovalProcess::getApprovalProcessId)
                        .collect(Collectors.toList())
        );
    }

    /**
     * @param approvalProcessId
     * @return
     */
    @Override
    public List<ApprovalStep> findByApprovalProcessId(Long approvalProcessId) {
        return approvalStepRepository.findApprovalStepsByApprovalProcessIdOrderBySerial(approvalProcessId);
    }


}
