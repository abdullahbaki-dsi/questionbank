package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.response.QuestionResponseDto;

import java.util.Optional;

public interface QuestionService {
    Optional<QuestionResponseDto> findById(Long id);
}
