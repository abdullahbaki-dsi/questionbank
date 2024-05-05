package org.dsi.com.approvalService.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.QuestionApprovalDto;
import org.dsi.com.approvalService.dto.Request.QuestionApprovalStatusDto;
import org.dsi.com.approvalService.event.PendingApprovalEvent;
import org.dsi.com.approvalService.model.QuestionApprovals;
import org.dsi.com.approvalService.service.ApprovalService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@Slf4j

public class ApprovalServiceImpl implements ApprovalService {
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, PendingApprovalEvent> kafkaTemplate;

    public ApprovalServiceImpl(WebClient.Builder webClientBuilder,
                               KafkaTemplate<String, PendingApprovalEvent> kafkaTemplate) {
        this.webClientBuilder = webClientBuilder;
        this.kafkaTemplate = kafkaTemplate;
    }


    /**
     * this will send a event to generate a mail to let user know they have new question to approve
     */
    public void generatePendingApprovalEvent(){
        //TODO:: need to add the user id to whom the mail needs to be sent
        kafkaTemplate.send( "pendingApprovalNotificationTopic",new PendingApprovalEvent());
    }

    /**
     * @param questionApprovals
     */
    @Override
    public void updateQuestionStatus(QuestionApprovals questionApprovals) {
        log.info("inside updateQuestionStatus");
        QuestionApprovalStatusDto.QuestionApprovalStatusDtoBuilder builder = QuestionApprovalStatusDto.builder();
        builder.status(questionApprovals.getStatusCode());
        builder.approvedDate(questionApprovals.getApprovedDate());
        QuestionApprovalStatusDto questionApprovalStatusDto = builder.build();
        try {
            webClientBuilder.build()
                    .put()
                    .uri("http://question-service/question-service/api/v1/question/" + questionApprovals.getQuestionId() + "/approve")
                    .bodyValue(questionApprovalStatusDto)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            log.error("Error while updating question status", e);
        }
    }
}
