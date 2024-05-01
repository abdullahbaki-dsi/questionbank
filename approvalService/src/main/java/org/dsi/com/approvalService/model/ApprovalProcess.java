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
@Table(name="approval_process")
public class ApprovalProcess {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String name;
    /**
     * user foreign key
     */
    private String CreatedByUserId;
    private boolean isDeleted;
}
