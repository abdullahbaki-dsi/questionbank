package org.dsi.com.approvalService.repository;

import jakarta.annotation.Nonnull;
import org.dsi.com.approvalService.model.CategoryApprovalProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryApprovalProcessRepository extends JpaRepository <CategoryApprovalProcess,Long> {
    List<CategoryApprovalProcess> findCategoryApprovalProcessesByCategoryId(Long categoryId);


    List<CategoryApprovalProcess> findCategoryApprovalProcessesByCategoryIdAndActivateStatusIgnoreCase(Long categoryId,
                                                                                                           String activateStatus);
    List<CategoryApprovalProcess> findCategoryApprovalProcessesByApprovalProcessId(Long approvalProcessId);
    void deleteById(@Nonnull Long categoryApprovalProcessId);

    Optional<CategoryApprovalProcess> findCategoryApprovalProcessByCategoryIdAndActivateStatusEqualsIgnoreCase(
            Long categoryApprovalProcessId, String activateStatus);


}