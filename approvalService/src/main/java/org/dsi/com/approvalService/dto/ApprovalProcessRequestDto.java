package org.dsi.com.approvalService.dto;

import lombok.*;

@Data
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ApprovalProcessRequestDto {
    private String name;
    private String CreatedByUserId;
}
