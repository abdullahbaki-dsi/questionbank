package org.dsi.com.questionService.service.implementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.questionService.dto.QuestionApprovalStatusDto;
import org.dsi.com.questionService.dto.QuestionRequestDto;
import org.dsi.com.questionService.model.Question;
import org.dsi.com.questionService.repository.QuestionRepository;
import org.dsi.com.questionService.service.QuestionService;
import org.dsi.com.questionService.utils.ApprovalStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public Question SaveQuestion(QuestionRequestDto questionRequest){
        Question.QuestionBuilder builder = Question.builder();
        builder.categoryID(questionRequest.getCategoryID());
        builder.difficultyLevel(questionRequest.getDifficultyLevel());
        builder.submittedByUserID(questionRequest.getSubmittedByUserID());
        builder.questionStatement(questionRequest.getQuestionStatement());
        builder.constraints(questionRequest.getConstraints());
        builder.sampleInput(questionRequest.getSampleInput());
        builder.sampleOutput(questionRequest.getSampleOutput());
        builder.submittedDate(new Date());
        builder.isDeleted(false);
        builder.approvalStatus(ApprovalStatus.PENDING.getCode());
        Question question = builder.build();
        return questionRepository.save(question);
    }


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public ResponseEntity<?> updateStatus(Long questionId, QuestionApprovalStatusDto questionApprovalStatusDto) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setApprovedDate(new Date());
            question.setApprovalStatus(ApprovalStatus.getCode(questionApprovalStatusDto.getStatus()));
            questionRepository.save(question);
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> getQuestionById(Long questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
