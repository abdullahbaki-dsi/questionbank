package org.dsi.com.userService.service;

import org.dsi.com.userService.dto.RoleDto;
import org.dsi.com.userService.dto.RoleRequestDto;
import org.dsi.com.userService.dto.UserRoleDto;
import org.dsi.com.userService.model.UserRoles;

import java.util.List;


public interface RolesService {
    RoleDto getRoleByRoleId(Long rolesID);

    List<RoleDto> getAllRoles();

    RoleDto createRole(RoleRequestDto roleRequestDto);

    UserRoles createUserRole(UserRoleDto userRoleDto);
}
