package org.dsi.com.userService.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.dsi.com.userService.model.UserRoles;
import org.dsi.com.userService.repository.UserRolesRepository;
import org.dsi.com.userService.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRolesServiceImpl implements UserRoleService {
    final UserRolesRepository userRolesRepository;

    public List<UserRoles> getUserRolesByUserID(Long userId){
      return userRolesRepository.findByUserId(userId);
    }
}
