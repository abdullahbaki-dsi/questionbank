package org.dsi.com.questionService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ValueGenerationType;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    private Long categoryID;
    private Long submittedByUserID;
    private int difficultyLevel;
    private String question;
    private String sampleInput;
    private String sampleOutput;
    private String Constraints;
    private Boolean isDeleted;
    private LocalDateTime submittedDate;
    private LocalDateTime approvedDate;
}
