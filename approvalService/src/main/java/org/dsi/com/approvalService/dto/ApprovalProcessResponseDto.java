package org.dsi.com.approvalService.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ApprovalProcessResponseDto {
    private Long categoryApprovalProcessID;
    private Long approvalProcessId;
    private String name;
    private String status;
}
