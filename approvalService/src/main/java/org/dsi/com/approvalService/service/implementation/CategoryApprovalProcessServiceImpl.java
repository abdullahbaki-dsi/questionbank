package org.dsi.com.approvalService.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.approvalService.dto.ApprovalProcessResponseDto;
import org.dsi.com.approvalService.dto.CategoryApprovalProcessDto;
import org.dsi.com.approvalService.dto.CategoryApprovalProcessResponseDto;
import org.dsi.com.approvalService.model.ApprovalProcess;
import org.dsi.com.approvalService.model.CategoryApprovalProcess;
import org.dsi.com.approvalService.repository.CategoryApprovalProcessRepository;
import org.dsi.com.approvalService.service.ApprovalProcessService;
import org.dsi.com.approvalService.service.CategoryApprovalProcessService;
import org.dsi.com.approvalService.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j

public class CategoryApprovalProcessServiceImpl implements CategoryApprovalProcessService {
    final CategoryApprovalProcessRepository categoryApprovalProcessRepository;
    final ApprovalProcessService approvalProcessService;

    public CategoryApprovalProcessServiceImpl(CategoryApprovalProcessRepository categoryApprovalProcessRepository,
                                              ApprovalProcessService approvalProcessService) {
        this.categoryApprovalProcessRepository = categoryApprovalProcessRepository;
        this.approvalProcessService = approvalProcessService;
    }


    /**
     * @param categoryApprovalProcessDto payload for categrory approval process creation
     * @return return newly created object
     */
    @Override
    public ResponseEntity<?> saveCategoryApprovalProcess(CategoryApprovalProcessDto categoryApprovalProcessDto) {
        CategoryApprovalProcess.CategoryApprovalProcessBuilder builder = CategoryApprovalProcess.builder();
        builder.approvalProcessId(categoryApprovalProcessDto.getApprovalProcessId());
        builder.categoryId(categoryApprovalProcessDto.getCategoryId());
        builder.activateStatus(Status.DRAFT.getCode());
        CategoryApprovalProcess categoryApprovalProcess= categoryApprovalProcessRepository.save(builder.build());
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoryApprovalProcess);


    }

    /**
     * @param categoryId 
     * @return
     */
    @Override
    public ResponseEntity<?> findByCategoryId(Long categoryId) {
        List<CategoryApprovalProcess> optionalCategoryApprovalProcess =
                categoryApprovalProcessRepository.findCategoryApprovalProcessesByCategoryId(categoryId);
        if (optionalCategoryApprovalProcess.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(optionalCategoryApprovalProcess);

    }

    /**
     * @param categoryId 
     * @param activateStatus
     * @return
     */
    @Override
    public List<CategoryApprovalProcess> findCategoryApprovalProcessesByCategoryIdAndActivateStatus(Long categoryId, String activateStatus) {
        String status = Status.getCode(activateStatus);
        return categoryApprovalProcessRepository.findCategoryApprovalProcessesByCategoryIdAndActivateStatusIgnoreCase(categoryId,status);
    }

    /**
     * @param approvalProcessId approval process id
     * @return returns categoryyApprovalProcess object
     */
    @Override
    public List<CategoryApprovalProcess> getCategoryApprovalProcessByApprovalProcessId(Long approvalProcessId) {
        return categoryApprovalProcessRepository
                .findCategoryApprovalProcessesByApprovalProcessId(approvalProcessId);
    }

    /**
     * @param categoryApprovalProcessId 
     * @return
     */
    @Override
    public boolean deleteCategoryApprovalProcessByCategoryApprovalProcessId(Long categoryApprovalProcessId) {
        //TODO:: implement delete
        return true;
    }

    /**
     * @param categoryId 
     * @return
     */
    @Override
    public Optional<CategoryApprovalProcess> findCategoryApprovalProcessByCategoryIdAndStatusActive(Long categoryId) {
        return  categoryApprovalProcessRepository.
                findCategoryApprovalProcessByCategoryIdAndActivateStatusEqualsIgnoreCase(categoryId, Status.ACTIVE.getCode());
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
            categoryApprovalProcessOptional.stream()
                    .findFirst()
                    .ifPresentOrElse(
                            approvalProcess -> {
                                categoryApprovalProcessResponseDto.setCategoryId(
                                        approvalProcess.getCategoryId());
                            },
                            () -> {
                                throw new NoSuchElementException("No category found");
                            }
                    );
            categoryApprovalProcessOptional.forEach(
                    categoryApprovalProcess ->
                            mapToCategoryApprovalProcessResponseDto(approvalProcesses, categoryApprovalProcess)
            );
            categoryApprovalProcessResponseDto.setApprovalProcesses(approvalProcesses);

//            Optional<CategoryApprovalProcess> found = Optional.empty();
//            for (CategoryApprovalProcess approvalProcess : categoryApprovalProcessOptional) {
//                found = Optional.of(approvalProcess);
//                break;
//            }
//            if (categoryApprovalProcessResponseDto.getCategoryId()!=null) {
//                categoryApprovalProcessResponseDto.setCategoryId(
//                        found.get().getCategoryId());

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
                     .status(Status.getDescription(categoryApprovalProcess.getActivateStatus()))
                     .build();
             approvalProcesses.add(approvalProcess);
         }

    }
}


