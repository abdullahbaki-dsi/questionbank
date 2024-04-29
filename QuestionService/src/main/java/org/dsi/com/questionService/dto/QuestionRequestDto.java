package org.dsi.com.questionService.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuestionRequestDto {
    private Long categoryID;
    private Long submittedByUserID;
    private int difficultyLevel;
    private String questionStatement;
    private String sampleInput;
    private String sampleOutput;
    private String constraints;
    private Boolean isDeleted;
}
