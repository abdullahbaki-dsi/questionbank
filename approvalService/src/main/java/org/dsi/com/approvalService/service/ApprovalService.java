package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.event.PendingApprovalEvent;
import org.dsi.com.approvalService.model.QuestionApprovals;

import java.util.UUID;

public interface ApprovalService {

    /**
     * this will send a event to generate a mail to let user know they have new question to approve
     */
    void generatePendingApprovalEvent();

    void updateQuestionStatus(QuestionApprovals questionApprovals);
}
