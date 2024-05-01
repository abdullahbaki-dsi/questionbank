package org.dsi.com.approvalService.dto;

import jakarta.annotation.Nonnull;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class FeedbackDto {
    @Nonnull
    private Long questionId;
    private Long approvalStepId;
    private String comment;
    private Long userId;
    private Date feedbackDate;
}
