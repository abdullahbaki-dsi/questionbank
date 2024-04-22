package org.dsi.com.approvalService.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/approval")
@RequiredArgsConstructor
@Slf4j
public class ApprovalController {
    public final UserService userService;

    @GetMapping(value = "", name = "getApprovalApi")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name="approval", fallbackMethod = "approvalFallBack")
    @TimeLimiter(name = "approval")
    @Retry(name="approval")
    public String getAllApprovals(){
        log.info("calling user service");
        return userService.getUsersList();
        //return " This is a ok reponse to test";
    }


    @GetMapping("/exception")
    @ResponseStatus(HttpStatus.OK)
    @Retry(name="approval")
    public Exception approveQuestion( ){
            return new Exception();
    }



    public String approvalFallBack(RuntimeException exception) {

        return "Exception happened";
    }

}
