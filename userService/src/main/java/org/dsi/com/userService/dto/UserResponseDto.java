package org.dsi.com.userService.dto;
import lombok.*;
import org.dsi.com.userService.model.Role;


import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Builder

public class UserResponseDto {

    private  Long userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Date lastLoginDate;
    private List<RoleDto> roles;
    private Boolean isDeleted;
}
