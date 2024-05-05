package org.dsi.com.approvalService.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ApproverGroupDto {
    private Long approvalStepId;
    private Long rolesId;
}
