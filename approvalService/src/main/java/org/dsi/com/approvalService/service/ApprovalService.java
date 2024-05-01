package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.event.PendingApprovalEvent;

import java.util.UUID;

public interface ApprovalService {

    /**
     * this will send a event to generate a mail to let user know they have new question to approve
     */
    public void generatePendingApprovalEvent();

}
