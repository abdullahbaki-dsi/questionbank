package org.dsi.com.approvalService.dto;

import lombok.*;

import org.dsi.com.approvalService.model.ApproverGroup;

import java.util.List;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ApprovalStepsResponseDto {
    private Long stepsId;
    private Long approvalProcessId;
    private String stepName;
    private String previousStep;
    private String nextStep;
    private int serial;
    private boolean isDeleted;
    private List<ApproverGroup> approverGroups;
}
