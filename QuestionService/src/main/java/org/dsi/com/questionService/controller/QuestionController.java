package org.dsi.com.questionService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/question")
@Slf4j
public class QuestionController {


    @GetMapping(value = "/", name = "getQuestionApi")
    @ResponseStatus(HttpStatus.OK)
    public String getAllQeustion(){
        return " This is a ok reponse to test";
    }

}
