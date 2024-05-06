package org.dsi.com.userService.controller;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.RoleDto;
import org.dsi.com.userService.dto.RoleRequestDto;
import org.dsi.com.userService.dto.UserRoleDto;
import org.dsi.com.userService.model.User;
import org.dsi.com.userService.model.UserRoles;
import org.dsi.com.userService.service.Implementation.UserRolesServiceImpl;
import org.dsi.com.userService.service.RolesService;
import org.dsi.com.userService.service.UserRoleService;
import org.dsi.com.userService.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@Slf4j

public class RoleController {

    final RolesService rolesService;
    final UserRoleService userRoleService;
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

    @GetMapping(value = "/user", name="A user all Role")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getRolesByUserId(@QueryParam("userId") Long userId){
        return ResponseEntity.ok().body(userRoleService.getUserRolesByUserID(userId));
    }

    @GetMapping(value = "/{roleID}", name=" get single Role")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto getRoleById(@PathVariable Long roleID){
        return rolesService.getRoleByRoleId(roleID);
    }

    @PostMapping(value="/user", name = "adding user to roles API")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRoles addRolesToAUser(@RequestBody UserRoleDto userRoleDto) {
        return  rolesService.createUserRole(userRoleDto);
    }

    @PostMapping(value = "/{roleID}/activate", name=" get single Role")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto activate(@PathVariable Long roleID){
        return rolesService.activateRole(roleID);
    }





}
