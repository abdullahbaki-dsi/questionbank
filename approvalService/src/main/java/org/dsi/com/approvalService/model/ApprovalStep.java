package org.dsi.com.approvalService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="approval_steps")
public class ApprovalStep {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    /**
     * approvalProcess foreign key
     */
    private Long approvalProcessId;
    private String stepName;
    private String previousStep;
    private String nextStep;
    private int serial;
    private boolean isDeleted;
}
