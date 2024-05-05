package org.dsi.com.approvalService.service;

import org.dsi.com.approvalService.dto.response.UserResponseDto;

public interface UserService {

    UserResponseDto getUserById(Long userId);
}
