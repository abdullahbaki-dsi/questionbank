package org.dsi.com.questionService.service;

import lombok.RequiredArgsConstructor;
import org.dsi.com.questionService.dto.QuestionRequest;
import org.dsi.com.questionService.model.Question;
import org.dsi.com.questionService.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question SaveQuestion(QuestionRequest questionRequest){
        Question question = new Question();
        question.setQuestion("how to reverse a string?");
        question.setIsDeleted(false);
        question.setDifficultyLevel(1);
        question.setSubmittedByUserID(Integer.toUnsignedLong(1));
        return questionRepository.save(question);
    }


    public List<Question> getAllQuestions() {
       return questionRepository.findAll();
    }
}
