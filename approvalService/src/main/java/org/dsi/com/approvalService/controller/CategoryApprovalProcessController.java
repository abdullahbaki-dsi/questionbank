package org.dsi.com.approvalService.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.CategoryApprovalProcessDto;
import org.dsi.com.approvalService.dto.Request.CategoryApprovalProcessStatusUpdateDto;
import org.dsi.com.approvalService.model.CategoryApprovalProcess;
import org.dsi.com.approvalService.service.CategoryApprovalProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category/process")
@Slf4j
public class CategoryApprovalProcessController {

    final CategoryApprovalProcessService categoryApprovalProcessService;

    public CategoryApprovalProcessController(CategoryApprovalProcessService categoryApprovalProcessService) {
        this.categoryApprovalProcessService = categoryApprovalProcessService;
    }

    @GetMapping(value = "/{categoryId}", name = "get category wise approval process Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getApprovalProcessByCategoryId(@PathVariable Long categoryId){
        return  categoryApprovalProcessService.findApprovalProcessByCategoryId(categoryId);
    }

    @PostMapping(value = "/{categoryApprovalProcessId}/activate", name = "add approval process to category")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> activateCategoryApprovalProcess(@PathVariable Long categoryApprovalProcessId,
                                                             @RequestBody CategoryApprovalProcessStatusUpdateDto categoryApprovalProcessStatusUpdateDto){
        Optional<CategoryApprovalProcess> categoryApprovalProcessOptional = Optional.empty();
        try {

            categoryApprovalProcessOptional = categoryApprovalProcessService.activateCategoryApprovalProcess(categoryApprovalProcessId,
                            categoryApprovalProcessStatusUpdateDto);
        }catch (IllegalArgumentException e){
            log.error("Error while activating approval process", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This category already has an active approval process");
        }
        if (categoryApprovalProcessOptional.isPresent()){
            return ResponseEntity.ok().body(categoryApprovalProcessOptional.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("payload is not valid");
    }


    @GetMapping(value = "/test/{categoryId}", name = "get category wise approval process Api")
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
    @GetMapping(value = "/cap/{categoryApprovalProcessId}", name = "get approval process Api")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<?> getCategoryApprovalProcessByCategoryApprovalProcessId(@PathVariable Long categoryApprovalProcessId){
        log.info("searching for {}", categoryApprovalProcessId);
        Optional<CategoryApprovalProcess> categoryApprovalProcess =
                categoryApprovalProcessService.findByID(categoryApprovalProcessId);
        if(categoryApprovalProcess.isPresent()){
            return ResponseEntity.ok().body(categoryApprovalProcess.get());
        }
        return ResponseEntity.noContent().build();
    }

}
