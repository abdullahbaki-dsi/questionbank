package org.dsi.com.userService.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
