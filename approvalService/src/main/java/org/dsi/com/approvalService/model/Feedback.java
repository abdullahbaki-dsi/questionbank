package org.dsi.com.approvalService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    /**
     * question id fk
     */
    private Long questionId;
    /**
     * approval steps in which feedback was given
     * approvl fk
     */
    private Long approvalStepId;
    private String comment;
    /**
     * user who provided feedback
     * user id fk
     */
    private Long userId;
    private Date feedbackDate;
    private Boolean isDeleted;
}
