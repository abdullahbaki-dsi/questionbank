package org.dsi.com.approvalService.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.ApprovalProcessRequestDto;
import org.dsi.com.approvalService.model.ApprovalProcess;
import org.dsi.com.approvalService.service.ApprovalProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/approval/process")
public class ApprovalProcessController {
    final ApprovalProcessService approvalProcessService;

    public ApprovalProcessController(ApprovalProcessService approvalProcessService) {
        this.approvalProcessService = approvalProcessService;
    }

    @GetMapping(value = "/", name = "get approval process Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllApprovalProcess(){
        List<ApprovalProcess> approvalProcessList = approvalProcessService.finaAll();
        if(approvalProcessList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(approvalProcessList);
    }
    @GetMapping(value = "/{approvalProcessId}", name = "get approval process Api  by id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllApprovalProcessById(@PathVariable Long approvalProcessId){
        Optional<ApprovalProcess> approvalProcessOptional = approvalProcessService.findById(approvalProcessId);
        if (approvalProcessOptional.isPresent()){
            return ResponseEntity.ok().body(approvalProcessOptional.get());
        }
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/", name = "create approval Process")
    @ResponseStatus (HttpStatus.CREATED)
    public ApprovalProcess saveApprovalProcess(@RequestBody ApprovalProcessRequestDto approvalProcessRequestDto){
        return approvalProcessService.save(approvalProcessRequestDto);
    }
}
