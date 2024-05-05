package org.dsi.com.approvalService.service;

import org.apache.coyote.BadRequestException;
import org.dsi.com.approvalService.dto.ApprovalStepDto;
import org.dsi.com.approvalService.model.ApprovalStep;

import java.util.List;
import java.util.Optional;

public interface ApprovalStepService {
    List<ApprovalStep> save(List<ApprovalStepDto> approvalStepDtos) throws BadRequestException;
    List<ApprovalStep> getApprovalSetpByApprovalProcessId(Long approvalProcessId);
    Optional <ApprovalStep> getApprovalStepById(Long approvalStepId);
    List<ApprovalStep> findAll();
    List<ApprovalStep> findByCategoryId(Long categoryId);
    List <ApprovalStep> findByCategoryIdAndActivateStatus(Long categoryId, String status);

    List<ApprovalStep> findByApprovalProcessId(Long approvalProcessId);
}




