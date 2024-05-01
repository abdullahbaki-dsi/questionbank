package org.dsi.com.notificationService.consumer;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.notificationService.event.PendingApprovalEvent;
import org.springframework.kafka.annotation.KafkaListener;
@Slf4j

public class NotificationConsumer {
    private String name;
//    @KafkaListener(topics = "pendingApprovalNotificationTopic")
//    public void handleMessage(PendingApprovalEvent pendingApprovalEvent){
//        //TODO:: send notification
//        log.info("message received {} ", pendingApprovalEvent.getQuestionID());
//
//    }
}
