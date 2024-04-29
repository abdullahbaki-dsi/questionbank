package org.dsi.com.userService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.UserCreateDto;
import org.dsi.com.userService.dto.UserResponseDto;
import org.dsi.com.userService.model.User;
import org.dsi.com.userService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.UUID;


public interface UserService {
    UserResponseDto saveUser(UserCreateDto userCreateDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserByUserID(Long userID);


//
}
