package org.dsi.com.approvalService.service.interServiceCommunication;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.response.QuestionResponseDto;
import org.dsi.com.approvalService.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.rmi.NoSuchObjectException;
import java.util.Optional;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService{
    private final WebClient QuestionServiceWebClientBuilder;

    public QuestionServiceImpl(WebClient.Builder webClientBuilder) {
        this.QuestionServiceWebClientBuilder = webClientBuilder
                .baseUrl("http://question-service")
                .build();
//        webClientBuilder.baseUrl("http://question-service");
    }

    @Override
    public Optional<QuestionResponseDto> findById(Long id) {
        QuestionResponseDto questionResponseDto = null;

       try {
           questionResponseDto = QuestionServiceWebClientBuilder
                   .get()
                   .uri("/question-service/api/v1/question/" + id)
                   .retrieve()
                   .onStatus(HttpStatus.OK::equals, clientResponse -> Mono.empty())
                   .onStatus(HttpStatusCode::isError, clientResponse -> {
                       log.error("Error while fetching question");
                       log.error(clientResponse.statusCode().toString());
                       return Mono.error(new NoSuchObjectException("Question not found"));
                   })
                   .bodyToMono(QuestionResponseDto.class)
                   .block();
       } catch (Exception e) {
           log.error("Error while fetching question", e);
           return Optional.empty();
       }
       return Optional.ofNullable(questionResponseDto);
    }
}
