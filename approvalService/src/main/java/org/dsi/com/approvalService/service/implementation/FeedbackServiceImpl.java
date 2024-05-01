package org.dsi.com.approvalService.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.FeedbackDto;
import org.dsi.com.approvalService.model.Feedback;
import org.dsi.com.approvalService.repository.FeedbackRepository;
import org.dsi.com.approvalService.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    final FeedbackRepository feedbackRepository;

    /**
     * @param questionId
     * @return
     */
    @Override
    public ResponseEntity<?> findByQuestionId(Long questionId) {
        List<Feedback> feedbacks= feedbackRepository.findFeedbackByQuestionId(questionId).stream().toList();
        if(feedbacks.isEmpty()){
             return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(feedbacks);
        }
    }

    /**
     * @param feedbackDto 
     * @return
     */
    @Override
    public Feedback saveFeedback(FeedbackDto feedbackDto) {
        Feedback.FeedbackBuilder builder = Feedback.builder();
        builder.comment(feedbackDto.getComment());
        builder.approvalStepId(feedbackDto.getApprovalStepId());
        builder.userId(feedbackDto.getUserId());
        builder.questionId(feedbackDto.getQuestionId());
        builder.feedbackDate(new Date());
        builder.isDeleted(Boolean.FALSE);
        
        return feedbackRepository.save(builder.build());
    }

    /**
     * @param feedbackId 
     * @return
     */
    @Override
    public ResponseEntity<?> deleteFeedback(Long feedbackId) {
          Optional<Feedback> feedback = feedbackRepository.findById(feedbackId);
          feedback.ifPresent(value -> value.setIsDeleted(Boolean.TRUE));
          try{
              feedbackRepository.save(feedback.get());
              return ResponseEntity.ok().body("Reource Has been deleted");
          } catch (Exception ex){
              return ResponseEntity.badRequest().build();
          }


    }

    /**
     * @return 
     */
    @Override
    public ResponseEntity<?> finaAll() {
        List<Feedback> feedbacks =feedbackRepository.findAll().stream().toList();

        if(feedbacks.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(feedbacks);
    }

    /**
     * @param feedbackId 
     * @return
     */
    @Override
    public ResponseEntity<?> find(Long feedbackId) {
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
        if(feedbackOptional.isPresent()){
            return ResponseEntity.ok().body(feedbackOptional.get());
        }
        return ResponseEntity.noContent().build();
    }
}
