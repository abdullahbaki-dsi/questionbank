package org.dsi.com.approvalService.repository;

import org.dsi.com.approvalService.model.ApproverGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApproverGroupRepository extends JpaRepository<ApproverGroup,Long> {

    List<ApproverGroup> findAllByApprovalStepIdAndIsDeleted(Long stepId, boolean isDeleted);

    Optional<ApproverGroup> findByApprovalStepIdAndRolesIdAndIsDeleted(Long approvalStepId, Long rolesId, Boolean aTrue);

    Optional<ApproverGroup> findByIdAndIsDeleted(Long approverGroupId, Boolean isDeleted);
}
