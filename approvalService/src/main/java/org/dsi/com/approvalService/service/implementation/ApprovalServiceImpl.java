package org.dsi.com.approvalService.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.event.PendingApprovalEvent;
import org.dsi.com.approvalService.service.ApprovalService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, PendingApprovalEvent> kafkaTemplate;


    /**
     * this will send a event to generate a mail to let user know they have new question to approve
     */
    public void generatePendingApprovalEvent(){
        kafkaTemplate.send( "pendingApprovalNotificationTopic",new PendingApprovalEvent(UUID.randomUUID().toString()));
    }
}
