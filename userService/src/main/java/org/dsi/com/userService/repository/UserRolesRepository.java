package org.dsi.com.userService.repository;

import org.dsi.com.userService.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    @Query("""
        select
            new UserRoles (
            ur.Id,
            ur.userId,
            ur.roleId
            )
            FROM
            UserRoles ur
            where
            ur.userId =:userId
    """)
    List<UserRoles> findByUserId(@Param("userId") Long UserId);
}
