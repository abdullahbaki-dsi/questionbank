package org.dsi.com.approvalService.dto.response;

import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class QuestionResponseDto {

    private  Long id;
    private Long categoryID;
    private Long submittedByUserID;
    private int difficultyLevel;
    private String questionStatement;
    private String sampleInput;
    private String sampleOutput;
    private String constraints;
    private Boolean isDeleted;
    private Date submittedDate;
    private Date approvedDate;
    private int approvalStatus;
}
