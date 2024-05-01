package org.dsi.com.notificationService.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PendingApprovalEvent {
    private String questionID;
//    private String assignedDate;
//    private String lastApprovedSteps;
//    private String lastApprovedDate;
}
