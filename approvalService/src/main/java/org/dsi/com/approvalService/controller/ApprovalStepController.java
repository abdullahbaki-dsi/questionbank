package org.dsi.com.approvalService.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.QueryParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.dsi.com.approvalService.dto.ApprovalStepDto;
import org.dsi.com.approvalService.model.ApprovalStep;
import org.dsi.com.approvalService.service.ApprovalStepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/approval/step")
@Tag(name = "Approval Step Controller", description = "Approval Step Controller")

public class ApprovalStepController {
    final ApprovalStepService approvalStepService;

    public ApprovalStepController(ApprovalStepService approvalStepService) {
        this.approvalStepService = approvalStepService;
    }

    @PostMapping(value = "/", name="create approval steps for a process")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createApprovalSteps(@RequestBody List<ApprovalStepDto> approvalStepDtos){
      try {
       List<ApprovalStep> approvalSteps= approvalStepService.save(approvalStepDtos);
       return ResponseEntity.status(HttpStatus.CREATED).body(approvalSteps);
      } catch (BadRequestException e) {
          log.info("error happened {}:", e.getMessage());
          return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      } catch (Exception exception){
          log.info("error happended {}:", exception.getMessage());
         throw exception;
      }
    }

    @GetMapping(value ="/category/{categoryId}", name="get approval step by category id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getApprovalStepByCategoryId(@PathVariable Long categoryId,
                                                         @RequestParam( name="status") String status) {
        List<ApprovalStep> approvalSteps =
                approvalStepService.findByCategoryIdAndActivateStatus(categoryId, status);
        if (approvalSteps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Approval step not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(approvalSteps);
    }
    @GetMapping(value ="/approvalprocess/{approvalProcessId}", name="get approval step by aprpovalprocessId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getApprovalStepByApprovalProcessId(@PathVariable Long approvalProcessId) {
        List<ApprovalStep> approvalSteps =
                approvalStepService.findByApprovalProcessId(approvalProcessId);
        if (approvalSteps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Approval step not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(approvalSteps);
    }

}
