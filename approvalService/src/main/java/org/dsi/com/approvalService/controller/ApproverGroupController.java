package org.dsi.com.approvalService.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.ApproverGroupDto;
import org.dsi.com.approvalService.model.ApproverGroup;
import org.dsi.com.approvalService.service.ApproverGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/approver/group")
public class ApproverGroupController {
    final ApproverGroupService approverGroupService;

    public ApproverGroupController(ApproverGroupService approverGroupService) {
        this.approverGroupService = approverGroupService;
    }

    @PostMapping(value = "/", name = "create approver group")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createApproverGroup(@RequestBody ApproverGroupDto approverGroupDto) {
        Optional<ApproverGroup> approverGroupOptional =
                       approverGroupService.createApproverGroup(approverGroupDto);
        return approverGroupOptional
                .map(approverGroup -> ResponseEntity.status(HttpStatus.CREATED).body(approverGroup))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }



    @GetMapping(value = "/step/{stepId}", name = "Get approver group by step id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getApproverGroupByStepId(@PathVariable("stepId") Long stepId) {
        log.info("Get approver group by step id: {}", stepId);
        return ResponseEntity.ok(approverGroupService.getAllApproverGroupsByStepId(stepId));
    }

    @DeleteMapping(value = "/{approverGroupId}", name = "Delete approver group by id")
    @ResponseStatus (HttpStatus.GONE)
    public ResponseEntity<?> deleteApproverGroup(@PathVariable("approverGroupId") Long approverGroupId){
        log.info("Delete approver group by id: {}", approverGroupId);
        try {
            approverGroupService.deleteApproverGroup(approverGroupId);
        } catch (Exception e) {
            log.error("Error deleting approver group by id: {}", approverGroupId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.GONE).build();
    }


}
