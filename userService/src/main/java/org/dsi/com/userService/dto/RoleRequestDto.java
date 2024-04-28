package org.dsi.com.userService.dto;

import jakarta.annotation.Nullable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleRequestDto {
    private String name;
    @Nullable
    private String status;
}
