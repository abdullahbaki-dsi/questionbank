package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.ApprovalProcessRequestDto;
import org.dsi.com.approvalService.model.ApprovalProcess;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ApprovalProcessService {
    ResponseEntity<?> finaAll();

    ApprovalProcess save(ApprovalProcessRequestDto approvalProcessRequestDto);

    ResponseEntity<?> findById(Long approvalProcessId);
    Optional<ApprovalProcess> findApprovalByID(Long approvalProcessId);
}
