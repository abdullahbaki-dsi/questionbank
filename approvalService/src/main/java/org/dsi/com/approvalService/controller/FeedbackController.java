package org.dsi.com.approvalService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.FeedbackDto;
import org.dsi.com.approvalService.model.Feedback;
import org.dsi.com.approvalService.repository.FeedbackRepository;
import org.dsi.com.approvalService.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    final FeedbackService feedbackService;

    @GetMapping(value = "/", name = "get feedback Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllFeedbacks(){
        return feedbackService.finaAll();
    }

    @GetMapping(value = "/{feedbackId}", name = "get feedback Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findFeedbackById(@PathVariable Long feedbackId){
        return feedbackService.find(feedbackId);
    }
    @PostMapping(value = "/", name = "post feedback")
    @ResponseStatus (HttpStatus.CREATED)
    public Feedback postFeedback(@RequestBody FeedbackDto feedbackDto){
      return feedbackService.saveFeedback(feedbackDto);
    }

    @GetMapping(value = "/question/{questionId}", name = "get feedback Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findByQuestionId(@PathVariable Long questionId){
        return feedbackService.findByQuestionId(questionId);
    }

    @DeleteMapping(value = "/{feedbackId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteFeedback(@PathVariable Long feedbackId){
        return feedbackService.deleteFeedback(feedbackId);
    }



}
