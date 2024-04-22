package org.dsi.com.approvalService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserService {
    public final WebClient.Builder webClientBuilder;

    public String getUsersList(){
        String response =
        webClientBuilder.build().get()
                .uri("http://user-service/user-service/api/v1/user/")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("response is ::"+response);
        return response;
    }


}
