package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.CategoryApprovalProcessDto;
import org.springframework.http.ResponseEntity;

public interface CategoryApprovalProcessService {
    ResponseEntity<?> findApprovalProcessByCategoryId(Long categoryId);

    ResponseEntity<?> saveCategoryApprovalProcess(CategoryApprovalProcessDto categoryApprovalProcessDto);

    ResponseEntity<?> findByCategoryId(Long categoryId);
}
