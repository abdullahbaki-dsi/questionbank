package org.dsi.com.userService.repository;

import org.dsi.com.userService.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesReposityory extends JpaRepository<UserRoles, Long> {
}
