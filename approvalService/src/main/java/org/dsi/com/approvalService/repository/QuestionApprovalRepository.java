package org.dsi.com.approvalService.repository;

import org.dsi.com.approvalService.model.QuestionApprovals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionApprovalRepository extends JpaRepository<QuestionApprovals, Long>{
    List<QuestionApprovals> findQuestionApprovalsByQuestionId(Long questionID);
}
