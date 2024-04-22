package org.dsi.com.userService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {


    @GetMapping(value = "/", name = "getUsersApi")
    @ResponseStatus(HttpStatus.OK)
    public String getAllUsers(){
        return " This is a ok reponse to test";
    }


}
