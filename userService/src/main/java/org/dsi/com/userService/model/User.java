package org.dsi.com.userService.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table (name="users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    @Column (unique = true)
    private String userName;
    private String firstName;
    private String lastName;
    @Column (unique = true)
    private String email;
    private String password;
    private LocalDateTime lastLoginDate;
    private Boolean isDeleted;
}
