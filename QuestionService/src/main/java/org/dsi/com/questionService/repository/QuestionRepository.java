package org.dsi.com.questionService.repository;

import org.dsi.com.questionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
