package org.dsi.com.approvalService.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.utils.Status;

@Slf4j
@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category_approvalProcess",
        uniqueConstraints = {@UniqueConstraint( columnNames = {"categoryId","approvalProcessId"})})
public class CategoryApprovalProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    /**
     * category foreign key from category of question service
     */
    private Long categoryId;
    /**
     * aprovalProcess foreign key
     */
    private Long approvalProcessId;
    /**
     * possible value = ACTIVE (A), Draft(P), INACTIVE (D)
     */
    private String activateStatus;
}
