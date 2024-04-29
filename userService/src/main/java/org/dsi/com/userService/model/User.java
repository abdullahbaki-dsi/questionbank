package org.dsi.com.userService.model;

import jakarta.persistence.*;
import lombok.*;
import org.dsi.com.userService.dto.RoleDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private Date lastLoginDate;
    private Date createdDate;
    private Date lastModifiedDate;
    private Boolean isDeleted;

}
