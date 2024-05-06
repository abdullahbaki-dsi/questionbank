package org.dsi.com.userService.controller;

import org.dsi.com.userService.dto.UserCreateDto;
import org.dsi.com.userService.dto.UserResponseDto;
import org.dsi.com.userService.dto.request.LoginRequestDto;
import org.dsi.com.userService.dto.response.Token;
import org.dsi.com.userService.service.Implementation.JwtUtil;
import org.dsi.com.userService.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    JwtUtil jwtUtil;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsers_returnsUserList() {
        UserResponseDto user = new UserResponseDto();
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        List<UserResponseDto> result = userController.getAllUsers();

        assertEquals(1, result.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void createUser_returnsCreatedUser() {
        UserCreateDto userCreateDto = new UserCreateDto();
        UserResponseDto userResponseDto = new UserResponseDto();
        when(userService.saveUser(userCreateDto)).thenReturn(userResponseDto);

        ResponseEntity<?> result = userController.createUser(userCreateDto);

        assertEquals(ResponseEntity.ok().body(userResponseDto), result);
        verify(userService, times(1)).saveUser(userCreateDto);
    }

    @Test
    public void getUserByUserID_returnsUser() {
        UserResponseDto userResponseDto = new UserResponseDto();
        when(userService.getUserByUserID(1L)).thenReturn(userResponseDto);

        UserResponseDto result = userController.getUserByUserID(1L);

        assertEquals(userResponseDto, result);
        verify(userService, times(1)).getUserByUserID(1L);
    }

    @Test
    public void loginUser_returnsToken() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        UserResponseDto userResponseDto = new UserResponseDto();
        Token token = new Token();
        when(userService.loginUser(loginRequestDto)).thenReturn(userResponseDto);
        when(jwtUtil.generateResponseToken(userResponseDto)).thenReturn(token);

        ResponseEntity<?> result = userController.loginUser(loginRequestDto);

        assertEquals(ResponseEntity.ok().body(token), result);
        verify(userService, times(1)).loginUser(loginRequestDto);
        verify(jwtUtil, times(1)).generateResponseToken(userResponseDto);
    }
}