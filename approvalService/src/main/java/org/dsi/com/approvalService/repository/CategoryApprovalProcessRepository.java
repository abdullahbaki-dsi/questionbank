package org.dsi.com.approvalService.repository;

import org.dsi.com.approvalService.model.CategoryApprovalProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryApprovalProcessRepository extends JpaRepository <CategoryApprovalProcess,Long> {
    List<CategoryApprovalProcess> findCategoryApprovalProcessesByCategoryId(Long categoryId);

}