package org.dsi.com.approvalService.service.interServiceCommunication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.dsi.com.approvalService.dto.response.UserResponseDto;
import org.dsi.com.approvalService.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.rmi.NoSuchObjectException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final WebClient userServiceWebClientBuilder;

    public UserServiceImpl(WebClient.Builder webClientBuilder) {

        this.userServiceWebClientBuilder = webClientBuilder
                                            .baseUrl("http://user-service")
                                            .build();
    }

    /**
     * @param userId
     * @implNote calls the user-service/api/v1/user/{id} api to get the user details
     * @return
     */
    @Override
    public UserResponseDto getUserById(Long userId)  {
        UserResponseDto userResponseDto=null;
        try {
          userResponseDto   =  userServiceWebClientBuilder
                    .get()
                    .uri("/user-service/api/v1/user/" + userId)
                    .retrieve()
                    .onStatus(HttpStatus.OK::equals, clientResponse -> Mono.empty())
                    .onStatus(HttpStatusCode::isError, clientResponse -> {
                        log.error("Error while fetching user details");
                        log.error(clientResponse.statusCode().toString());
                        return Mono.error(new NoSuchObjectException("User not found"));
                    })
                    .bodyToMono(UserResponseDto.class)
                    .block();

        } catch (Exception e) {
            log.error("Error while fetching user details", e);
            return userResponseDto;
        }

        return userResponseDto;
    }
}
