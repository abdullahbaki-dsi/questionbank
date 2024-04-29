package org.dsi.com.userService.dto;

import lombok.*;
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRoleDto {
    private Long userId;
    private Long roleId;
}
