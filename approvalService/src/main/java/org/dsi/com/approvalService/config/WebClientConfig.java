package org.dsi.com.approvalService.config;

import org.dsi.com.approvalService.event.PendingApprovalEvent;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;


@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient()  {
        return WebClient.builder();
    }
}

