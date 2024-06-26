package org.dsi.com.approvalService.repository;

import org.dsi.com.approvalService.model.QuestionApprovals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionApprovalsRepository extends JpaRepository<QuestionApprovals, Long> {
}
