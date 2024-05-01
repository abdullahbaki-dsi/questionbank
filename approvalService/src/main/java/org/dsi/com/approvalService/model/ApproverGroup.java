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
@Table(name="approver_group")
public class ApproverGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    /**
     * approval_Step foreign key
     */
    private Long approvalStepId;
    /**
     * roles foreign key
     */
    private Long rolesId;
}
