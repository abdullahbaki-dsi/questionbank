package org.dsi.com.approvalService.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class QuestionApprovalDto {
    @NotNull
    private  Long approvalStepId;
    @NotNull
    private  Long approverUserId;
    @NotNull
    private  Long approverGroupId;
    /**
     * Approval status
     * 0 -> rejected
     * 1 -> approved
     * 2-> pending
     * 3-> request modification/clarity
     */
    @NotNull
    private String statusCode;

}
