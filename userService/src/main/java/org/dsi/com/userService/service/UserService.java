package org.dsi.com.userService.service;

import org.dsi.com.userService.dto.UserCreateDto;
import org.dsi.com.userService.dto.UserResponseDto;
import org.dsi.com.userService.dto.request.LoginRequestDto;
import java.util.List;


public interface UserService {
    UserResponseDto saveUser(UserCreateDto userCreateDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserByUserID(Long userID);

    UserResponseDto loginUser(LoginRequestDto loginRequestDto);


//
}
