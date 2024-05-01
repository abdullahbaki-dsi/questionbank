package org.dsi.com.approvalService.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.ApprovalProcessRequestDto;
import org.dsi.com.approvalService.model.ApprovalProcess;
import org.dsi.com.approvalService.repository.ApprovalProcessRepository;
import org.dsi.com.approvalService.service.ApprovalProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApprovalProcessServiceImpl implements ApprovalProcessService {
    final ApprovalProcessRepository approvalProcessRepository;

    /**
     * @return 
     */
    @Override
    public ResponseEntity<?> finaAll() {
        List<ApprovalProcess> approvalProcesses =approvalProcessRepository.findAll().stream().toList();

        if(approvalProcesses.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(approvalProcesses);
    }

    /**
     * @param approvalProcessRequestDto 
     * @return
     */
    @Override
    public ApprovalProcess save(ApprovalProcessRequestDto approvalProcessRequestDto) {
        ApprovalProcess.ApprovalProcessBuilder builder = ApprovalProcess.builder();
        builder.CreatedByUserId(approvalProcessRequestDto.getCreatedByUserId());
        builder.isDeleted(Boolean.FALSE);
        builder.name(approvalProcessRequestDto.getName());
        return approvalProcessRepository.save(builder.build());
    }

    /**
     * @param approvalProcessId 
     * @return
     */
    @Override
    public ResponseEntity<?> findById(Long approvalProcessId) {
       Optional<ApprovalProcess> approvalProcessOptional =
               approvalProcessRepository.findById(approvalProcessId);
       if(approvalProcessOptional.isPresent()){
           return ResponseEntity.ok().body(approvalProcessOptional.get());
       }
       return ResponseEntity.noContent().build();
    }

    /**
     * @param approvalProcessId
     * @return
     */
    @Override
    public Optional<ApprovalProcess> findApprovalByID(Long approvalProcessId) {
        return approvalProcessRepository.findById(approvalProcessId);
    }
}
