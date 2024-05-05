package org.dsi.com.approvalService.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserResponseDto {
    private  Long userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Date lastLoginDate;
    private List<RoleResponseDto> roles;
    private Boolean isDeleted;
}
