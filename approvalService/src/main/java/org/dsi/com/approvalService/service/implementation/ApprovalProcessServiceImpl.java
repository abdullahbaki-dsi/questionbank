package org.dsi.com.approvalService.service.implementation;

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
public class ApprovalProcessServiceImpl implements ApprovalProcessService {
    final ApprovalProcessRepository approvalProcessRepository;

    public ApprovalProcessServiceImpl(ApprovalProcessRepository approvalProcessRepository) {
        this.approvalProcessRepository = approvalProcessRepository;
    }

    /**
     * @return 
     */
    @Override
    public List<ApprovalProcess> finaAll() {
         return approvalProcessRepository.findAll().stream().toList();


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
    public Optional<ApprovalProcess> findById(Long approvalProcessId) {
       return approvalProcessRepository.findById(approvalProcessId);
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
