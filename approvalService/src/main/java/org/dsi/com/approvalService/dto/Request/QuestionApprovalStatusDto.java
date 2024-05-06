package org.dsi.com.approvalService.dto.Request;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionApprovalStatusDto {
    private String status;
    private Date approvedDate;
}