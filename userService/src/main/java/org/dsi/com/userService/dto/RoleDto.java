package org.dsi.com.userService.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class RoleDto {
    private Long roleId;
    private String name;
    private String status;
}
