package org.dsi.com.approvalService.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleResponseDto {
    private Long roleId;
    private String name;
    private String status;
}

