package org.dsi.com.approvalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor

public class ApprovalService {

    private final WebClient.Builder webClientBuilder;


}
