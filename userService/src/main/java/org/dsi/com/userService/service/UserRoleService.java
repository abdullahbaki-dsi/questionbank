package org.dsi.com.userService.service;

import org.dsi.com.userService.model.UserRoles;

import java.util.List;

public interface UserRoleService {
    List<UserRoles> getUserRolesByUserID(Long userId);
}
