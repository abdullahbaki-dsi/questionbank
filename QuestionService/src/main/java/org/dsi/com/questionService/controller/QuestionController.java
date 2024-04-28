package org.dsi.com.questionService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.questionService.dto.QuestionRequest;
import org.dsi.com.questionService.model.Question;
import org.dsi.com.questionService.service.QuestionService;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
@Slf4j
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    @GetMapping(value = "/", name = "getQuestionApi")
    @ResponseStatus(HttpStatus.OK)
    public List<Question> getAllQuestion(){
        log.info("inside getAll");
        return questionService.getAllQuestions();
    }

    @PostMapping (value = "/" , name= "creare Question")
    @ResponseStatus (HttpStatus.CREATED)
    public Question create(){
        return questionService.SaveQuestion(new QuestionRequest());
//        return ResponseStatus
    }

}
