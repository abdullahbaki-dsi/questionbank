package org.dsi.com.userService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name="role_features")
public class RolesFeatures {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long Id;
    private Long roleId;
    private Long featureId;

}
