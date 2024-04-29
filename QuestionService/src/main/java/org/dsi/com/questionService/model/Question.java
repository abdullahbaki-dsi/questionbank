package org.dsi.com.questionService.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ValueGenerationType;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
}
