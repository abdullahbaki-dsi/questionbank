package org.dsi.com.approvalService.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class CategoryApprovalProcessResponseDto {

    private Long categoryId;
    private List<ApprovalProcessResponseDto> approvalProcesses;

}
