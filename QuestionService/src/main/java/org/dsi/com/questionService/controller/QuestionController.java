package org.dsi.com.questionService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.questionService.dto.QuestionRequestDto;
import org.dsi.com.questionService.model.Question;
import org.dsi.com.questionService.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping (value = "/" , name= "create Question")
    @ResponseStatus (HttpStatus.CREATED)
    public Question create(@RequestBody QuestionRequestDto questionRequest){
        return questionService.SaveQuestion(questionRequest);
    }

    @GetMapping(value = "/{questionId}", name = "getQuestionApi")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId){
        log.info("inside get single question");

        return questionService.getQuestionById(questionId);
    }
    @PutMapping(value = "/{questionId}", name = "getQuestionApi")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId,
                                         @RequestBody Question question){
        log.info("inside get single question");

        return questionService.getQuestionById(questionId);
    }

}
