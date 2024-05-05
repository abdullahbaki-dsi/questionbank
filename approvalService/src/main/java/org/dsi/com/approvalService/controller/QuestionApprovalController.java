package org.dsi.com.approvalService.controller;

import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.dsi.com.approvalService.dto.QuestionApprovalDto;
import org.dsi.com.approvalService.dto.response.QuestionResponseDto;
import org.dsi.com.approvalService.dto.response.UserResponseDto;
import org.dsi.com.approvalService.model.QuestionApprovals;
import org.dsi.com.approvalService.service.QuestionApprovalService;
import org.dsi.com.approvalService.service.QuestionService;
import org.dsi.com.approvalService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/question/approval")
public class QuestionApprovalController {
    final QuestionApprovalService questionApprovalService;

    final UserService userService;
    final QuestionService questionService;

    public QuestionApprovalController(QuestionApprovalService questionApprovalService,
                                      UserService userService,
                                      QuestionService questionService) throws BadRequestException {
        this.questionApprovalService = questionApprovalService;
        this.userService = userService;
        this.questionService = questionService;
    }

    @GetMapping(value = "/{questionID}", name="get all approvals for a question id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getApprovalByQuestionId(@PathVariable Long questionID) {
        List<QuestionApprovals> questionApprovals = questionApprovalService.findByQuestionId(questionID);
        if (questionApprovals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question approval not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionApprovals);
    }

    @PostMapping (value = "/{questionID}", name="create approval for a question id")
    @TimeLimiter(name = "approval")
    @Retry(name="approval")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createApprovalForQuestion(@PathVariable Long questionID,
                                                       @RequestBody QuestionApprovalDto questionApprovalDto) {
        Optional<QuestionApprovals> approvalOptional =
                questionApprovalService.createApprovalForQuestion(questionID, questionApprovalDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Question approval created successfully");
    }

    @GetMapping(value = "/test/{id}", name="update approval for a question id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> testWebclinet(@PathVariable Long id) throws BadRequestException {
             Optional<QuestionResponseDto> responseDto;
//        try {
//          userResponseDto  = userService.getUserById(id);
//          if(userResponseDto  == null){
//              return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found");
//          }


        try {
            responseDto = questionService.findById(id);
        }
        catch (Exception e) {
            log.error("Error while fetching user details", e);
            throw new BadRequestException("Error while fetching user details");
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }



}
