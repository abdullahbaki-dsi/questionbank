package org.dsi.com.userService.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.UserCreateDto;
import org.dsi.com.userService.dto.UserResponseDto;
import org.dsi.com.userService.dto.request.LoginRequestDto;
import org.dsi.com.userService.dto.response.Token;
import org.dsi.com.userService.service.Implementation.JwtUtil;
import org.dsi.com.userService.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")

@Slf4j
public class UserController {
    final UserService userService;
    final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(value = "/", name = "getUsersApi")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping (value = "/register", name= "Create User Api")
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto userCreateDto){
        try
        {
            UserResponseDto user = userService.saveUser(userCreateDto);
           return  ResponseEntity.ok().body(user);

        }catch (DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username/Email Exists") ;
        }

    }

    @GetMapping(value = "/{userId}", name = "get Single User APi")
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoSuchObjectException.class)
    public UserResponseDto getUserByUserID(@PathVariable Long userId) {
           return userService.getUserByUserID(userId);
    }


    @PostMapping (value = "/login", name= "Login User Api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequestDto){
        try
        {
            UserResponseDto user = userService.loginUser(loginRequestDto);
            if(user != null) {
                Token token = jwtUtil.generateResponseToken(user);
                return ResponseEntity.ok().body(token);
            }
            throw new DataIntegrityViolationException("Username/password incorrect");

        }catch (DataIntegrityViolationException ex){
            log.error("Error while logging in", ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/password incorrect") ;
        }
    }




}
