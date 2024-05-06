package org.dsi.com.approvalService.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor

public class CategoryApprovalProcessStatusUpdateDto {

    private Long categoryId;
    private Long approvalProcessId;
    private String activateStatus;
}
