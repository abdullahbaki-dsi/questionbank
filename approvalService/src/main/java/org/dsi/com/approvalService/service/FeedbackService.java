package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.FeedbackDto;
import org.dsi.com.approvalService.model.Feedback;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {
    ResponseEntity<?> findByQuestionId(Long questionId);

    Feedback saveFeedback(FeedbackDto feedbackDto);

    ResponseEntity<?> deleteFeedback(Long feedbackId);

    ResponseEntity<?> finaAll();

    ResponseEntity<?> find(Long feedbackId);
}
