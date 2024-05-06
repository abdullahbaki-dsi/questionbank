package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.ApprovalProcessRequestDto;
import org.dsi.com.approvalService.model.ApprovalProcess;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ApprovalProcessService {
    List<ApprovalProcess> finaAll();

    ApprovalProcess save(ApprovalProcessRequestDto approvalProcessRequestDto);

    Optional <ApprovalProcess> findById(Long approvalProcessId);
    Optional<ApprovalProcess> findApprovalByID(Long approvalProcessId);
}
