package org.dsi.com.userService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.model.User;
import org.dsi.com.userService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    final UserService userService;
    @GetMapping(value = "/", name = "getUsersApi")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping (value = "/", name= "Create User Api")
    @ResponseStatus (HttpStatus.CREATED)
    public User createUser(){
        return userService.saveUser(new User());
    }


}
