package org.dsi.com.approvalService.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ApprovalStepDto {

    private Long approvalProcessId;
    private String stepName;
    private String previousStep;
    private String nextStep;
    private int serial;
    private boolean isDeleted;
}
