package org.dsi.com.questionService.service;

import org.dsi.com.questionService.dto.QuestionApprovalStatusDto;
import org.dsi.com.questionService.dto.QuestionRequestDto;
import org.dsi.com.questionService.model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<?> getQuestionById(Long questionId);
    Question SaveQuestion(QuestionRequestDto questionRequest);
    List<Question> getAllQuestions();

    ResponseEntity<?> updateStatus(Long questionId, QuestionApprovalStatusDto questionApprovalStatusDto);
}
