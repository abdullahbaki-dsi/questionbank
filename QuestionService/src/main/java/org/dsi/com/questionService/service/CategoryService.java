package org.dsi.com.questionService.service;

import org.dsi.com.questionService.dto.CategoryRequestDto;
import org.dsi.com.questionService.dto.QuestionRequestDto;
import org.dsi.com.questionService.model.Category;
import org.dsi.com.questionService.model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<?> getCategoryById(Long categoryId);
    Category saveCategory(CategoryRequestDto categoryRequestDto);
    List<Category> getAllCategories();
}
