package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.ApproverGroupDto;
import org.dsi.com.approvalService.model.ApproverGroup;

import java.util.List;
import java.util.Optional;

public interface ApproverGroupService {

    List<ApproverGroup> getAllApproverGroupsByStepId(Long stepId);

    Optional<ApproverGroup> createApproverGroup(ApproverGroupDto approverGroupDto);

    void deleteApproverGroup(Long approverGroupId);

    Optional<ApproverGroup> getByIdAndNotDeleted(Long approverGroupId);
}
