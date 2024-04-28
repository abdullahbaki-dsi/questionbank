package org.dsi.com.userService.service;

import org.dsi.com.userService.dto.RoleDto;
import org.dsi.com.userService.dto.RoleRequestDto;

import java.util.List;


public interface RolesService {
    RoleDto getRoleByRoleId(Long rolesID);

    List<RoleDto> getAllRoles();

    RoleDto createRole(RoleRequestDto roleRequestDto);
}
