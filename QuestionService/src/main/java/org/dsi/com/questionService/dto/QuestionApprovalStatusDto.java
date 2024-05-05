package org.dsi.com.questionService.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class QuestionApprovalStatusDto {
    private String status;
    private Date approvedDate;
}
