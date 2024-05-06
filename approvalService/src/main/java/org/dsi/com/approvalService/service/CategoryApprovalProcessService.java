package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.CategoryApprovalProcessDto;
import org.dsi.com.approvalService.dto.Request.CategoryApprovalProcessStatusUpdateDto;
import org.dsi.com.approvalService.model.CategoryApprovalProcess;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryApprovalProcessService {
    ResponseEntity<?> findApprovalProcessByCategoryId(Long categoryId);

    ResponseEntity<?> saveCategoryApprovalProcess(CategoryApprovalProcessDto categoryApprovalProcessDto);

    ResponseEntity<?> findByCategoryId(Long categoryId);

    List<CategoryApprovalProcess> findCategoryApprovalProcessesByCategoryIdAndActivateStatus(Long categoryId,
                                                                                             String activateStatus);

    List<CategoryApprovalProcess> getCategoryApprovalProcessByApprovalProcessId(Long approvalProcessId);
    boolean deleteCategoryApprovalProcessByCategoryApprovalProcessId(Long categoryApprovalProcessId);


    Optional<CategoryApprovalProcess> findCategoryApprovalProcessByCategoryIdAndStatusActive(Long categoryId);

    Optional<CategoryApprovalProcess> activateCategoryApprovalProcess(Long categoryProcessId, CategoryApprovalProcessStatusUpdateDto categoryApprovalProcessStatusUpdateDto);

    Optional<CategoryApprovalProcess> findByID(Long categoryApprovalProcessId);
}
