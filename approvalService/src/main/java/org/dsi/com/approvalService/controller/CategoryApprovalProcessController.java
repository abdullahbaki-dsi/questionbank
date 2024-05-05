package org.dsi.com.approvalService.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.CategoryApprovalProcessDto;
import org.dsi.com.approvalService.service.CategoryApprovalProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category/process")
@Slf4j
public class CategoryApprovalProcessController {

    final CategoryApprovalProcessService categoryApprovalProcessService;

    public CategoryApprovalProcessController(CategoryApprovalProcessService categoryApprovalProcessService) {
        this.categoryApprovalProcessService = categoryApprovalProcessService;
    }

    @GetMapping(value = "/{categoryId}", name = "get categorywise approval process Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getApprovalProcessByCategoryId(@PathVariable Long categoryId){
        return  categoryApprovalProcessService.findApprovalProcessByCategoryId(categoryId);
    }

    @GetMapping(value = "/test/{categoryId}", name = "get categorywise approval process Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCategoryApprovalProcessByCategoryId(@PathVariable Long categoryId){
        return  categoryApprovalProcessService.findByCategoryId(categoryId);
    }


    @PostMapping(value = "/", name = "add approval process to category")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveCategoryApprovalProcess(@RequestBody CategoryApprovalProcessDto
                                                                     categoryApprovalProcessDto){
        return categoryApprovalProcessService.saveCategoryApprovalProcess(categoryApprovalProcessDto);
    }
}
