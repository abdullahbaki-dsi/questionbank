package org.dsi.com.questionService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.questionService.dto.CategoryRequestDto;
import org.dsi.com.questionService.model.Category;
import org.dsi.com.questionService.model.Question;
import org.dsi.com.questionService.service.CategoryService;
import org.dsi.com.questionService.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping(value = "/", name = "get Category Api")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategory(){
        log.info("inside getAll");
        return categoryService.getAllCategories();
    }
    @PostMapping(value = "/", name = "create Category Api")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return categoryService.saveCategory(categoryRequestDto);
    }

    @GetMapping(value = "/{categoryId}", name = "get Category by Id Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }


}

