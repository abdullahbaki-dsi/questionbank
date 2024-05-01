package org.dsi.com.approvalService.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryApprovalProcessDto {
    private Long categoryId;
    private Long approvalProcessId;
    /**
     * possible value = ACTIVE (A), Draft(P), INACTIVE (D)
     */
    private String activateStatus;
}
