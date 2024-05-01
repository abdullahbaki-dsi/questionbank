package org.dsi.com.approvalService.service.implementation;

import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.ApprovalProcessResponseDto;
import org.dsi.com.approvalService.dto.CategoryApprovalProcessDto;
import org.dsi.com.approvalService.dto.CategoryApprovalProcessResponseDto;
import org.dsi.com.approvalService.model.ApprovalProcess;
import org.dsi.com.approvalService.model.CategoryApprovalProcess;
import org.dsi.com.approvalService.repository.CategoryApprovalProcessRepository;
import org.dsi.com.approvalService.service.ApprovalProcessService;
import org.dsi.com.approvalService.service.CategoryApprovalProcessService;
import org.dsi.com.approvalService.utils.ApprovalStatus;
import org.dsi.com.approvalService.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryApprovalProcessServiceImpl implements CategoryApprovalProcessService {
    final CategoryApprovalProcessRepository categoryApprovalProcessRepository;
    final ApprovalProcessService approvalProcessService;


    /**
     * @param categoryApprovalProcessDto
     * @return
     */
    @Override
    public ResponseEntity<?> saveCategoryApprovalProcess(CategoryApprovalProcessDto categoryApprovalProcessDto) {
        CategoryApprovalProcess.CategoryApprovalProcessBuilder builder = CategoryApprovalProcess.builder();
        builder.approvalProcessId(categoryApprovalProcessDto.getApprovalProcessId());
        builder.categoryId(categoryApprovalProcessDto.getCategoryId());
        builder.activateStatus(Status.DRAFT.getName());
        CategoryApprovalProcess categoryApprovalProcess= categoryApprovalProcessRepository.save(builder.build());
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoryApprovalProcess);


    }

    /**
     * @param categoryId 
     * @return
     */
    @Override
    public ResponseEntity<?> findByCategoryId(Long categoryId) {
        log.error("here");
        List<CategoryApprovalProcess> optionalCategoryApprovalProcess =
                categoryApprovalProcessRepository.findCategoryApprovalProcessesByCategoryId(categoryId);
        if (optionalCategoryApprovalProcess.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(optionalCategoryApprovalProcess);

    }


    /**
     * @param categoryId 
     * @return
     */
    @Override
    public ResponseEntity<?> findApprovalProcessByCategoryId(Long categoryId) {
        CategoryApprovalProcessResponseDto categoryApprovalProcessResponseDto = new CategoryApprovalProcessResponseDto();
        List<ApprovalProcessResponseDto> approvalProcesses = new ArrayList<ApprovalProcessResponseDto>();

        try {
            List<CategoryApprovalProcess> categoryApprovalProcessOptional =
                    categoryApprovalProcessRepository.findCategoryApprovalProcessesByCategoryId(categoryId);
            Optional<CategoryApprovalProcess> found = Optional.empty();
            for (CategoryApprovalProcess approvalProcess : categoryApprovalProcessOptional) {
                found = Optional.of(approvalProcess);
                break;
            }
            if (found.isPresent()) {
                categoryApprovalProcessResponseDto.setCategoryId(
                        found.get().getCategoryId());
                categoryApprovalProcessOptional.forEach(
                    categoryApprovalProcess -> mapToCategoryApprovalProcessResponseDto(approvalProcesses, categoryApprovalProcess)
                );
                categoryApprovalProcessResponseDto.setApprovalProcesses(approvalProcesses);
            }
            else  throw new NoSuchElementException("");
        } catch (NoSuchElementException exception){
            log.error("Error occurred {}",
                    exception.getMessage());
            throw new NoSuchElementException("No category found");
        }
        return ResponseEntity.ok().body(categoryApprovalProcessResponseDto);
    }



    private void mapToCategoryApprovalProcessResponseDto(
            List<ApprovalProcessResponseDto> approvalProcesses ,
            CategoryApprovalProcess categoryApprovalProcess) {
        Long approvalProcessId = categoryApprovalProcess.getApprovalProcessId();
        Optional <ApprovalProcess> approvalProcessOptional = approvalProcessService.findApprovalByID(approvalProcessId);

         if(approvalProcessOptional.isPresent()){
            ApprovalProcessResponseDto approvalProcess= ApprovalProcessResponseDto.builder()
                     .approvalProcessId(approvalProcessOptional.get().getId())
                     .name(approvalProcessOptional.get().getName())
                     .categoryApprovalProcessID(categoryApprovalProcess.getId())
                     .status(Status.getDesc(categoryApprovalProcess.getActivateStatus()))
                     .build();
             approvalProcesses.add(approvalProcess);
         }

    }
}


