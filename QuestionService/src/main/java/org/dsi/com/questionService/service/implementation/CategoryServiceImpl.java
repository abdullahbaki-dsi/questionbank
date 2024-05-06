package org.dsi.com.questionService.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.questionService.dto.CategoryRequestDto;
import org.dsi.com.questionService.model.Category;
import org.dsi.com.questionService.model.Question;
import org.dsi.com.questionService.repository.CategoryRepository;
import org.dsi.com.questionService.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;

    /**
     * @param categoryId 
     * @return
     */
    @Override
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    /**
     * @return
     */
    @Override
    public List<Category> getAllCategories() {
        return  categoryRepository.findAll();

    }

    /**
     * @param categoryRequestDto 
     * @return
     */
    @Override
    public Category saveCategory(CategoryRequestDto categoryRequestDto) {
        Category.CategoryBuilder builder = Category.builder();
        builder.Name(categoryRequestDto.getName());
        builder.isDeleted(false);
        return categoryRepository.save(builder.build());
    }


}
