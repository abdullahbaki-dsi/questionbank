package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.QuestionApprovalDto;
import org.dsi.com.approvalService.model.QuestionApprovals;

import java.util.List;
import java.util.Optional;

public interface QuestionApprovalService {
    List<QuestionApprovals> findByQuestionId(Long questionID);

    Optional<QuestionApprovals> createApprovalForQuestion(Long questionID, QuestionApprovalDto questionApprovalDto) throws Exception;
}
