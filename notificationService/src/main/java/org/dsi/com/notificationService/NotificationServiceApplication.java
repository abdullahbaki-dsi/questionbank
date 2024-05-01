package org.dsi.com.notificationService;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.notificationService.event.PendingApprovalEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
    @KafkaListener(topics = "pendingApprovalNotificationTopic")
    public void handleMessage(PendingApprovalEvent pendingApprovalEvent){
        //TODO:: send notification
        log.info("message received {} ", pendingApprovalEvent.getQuestionID());

    }

}
