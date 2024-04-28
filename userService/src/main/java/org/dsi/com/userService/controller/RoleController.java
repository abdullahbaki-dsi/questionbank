package org.dsi.com.userService.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.RoleDto;
import org.dsi.com.userService.dto.RoleRequestDto;
import org.dsi.com.userService.model.User;
import org.dsi.com.userService.service.RolesService;
import org.dsi.com.userService.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@Slf4j

public class RoleController {

    final RolesService rolesService;
    @GetMapping(value = "/", name = "get roles api")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> getAllRoles(){
        return rolesService.getAllRoles();
    }

    @PostMapping(value = "/", name ="Create role")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto createRole( @RequestBody RoleRequestDto roleRequestDto){
        return rolesService.createRole(roleRequestDto);
    }


    @GetMapping(value = "/{roleID}", name=" get single Role")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto getRoleById(@PathVariable Long roleID){
       return rolesService.getRoleByRoleId(roleID);
    }


}
