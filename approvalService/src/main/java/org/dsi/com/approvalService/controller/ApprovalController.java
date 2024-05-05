package org.dsi.com.approvalService.controller;

import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.service.ApprovalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/approval")
@Slf4j
public class ApprovalController {
    public final ApprovalService approvalService;

    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

//    @GetMapping(value = "", name = "getApprovalApi")
//    @ResponseStatus(HttpStatus.OK)
//    @CircuitBreaker(name="approval", fallbackMethod = "approvalFallBack")
//    @TimeLimiter(name = "approval")
//    @Retry(name="approval")
//    public String getAllApprovals(){
//        log.info("calling user service");
//        return userService.getUsersList();
//    }


    @GetMapping(value = "/", name = "get Approvals Api")
    @ResponseStatus(HttpStatus.OK)
    public String getAllApprovals(){
        approvalService.generatePendingApprovalEvent();
        return "OK";

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
