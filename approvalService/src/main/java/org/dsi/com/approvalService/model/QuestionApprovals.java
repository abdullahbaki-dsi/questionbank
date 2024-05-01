package org.dsi.com.approvalService.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_approvals")
public class QuestionApprovals {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    /**
     * question FK
     */
    private Long questionId;
    /**
     * approval_step fk
     */
    private  Long approvalStepId;
    /**
     * Approver's userID fk
     */
    private  Long approverUserId;
    /**
     * approver User's group fk
     */
    private  Long approverGroupId;
    /**
     * approver user's role Id fk
     */
    private  Long rolesId;
    /**
     * Approval status
     * 0 -> rejected
     * 1 -> approved
     * 2-> pending
     * 3-> request modification/clarity
     */
    private  int statusCode;
    private Date approvedDate;

}
