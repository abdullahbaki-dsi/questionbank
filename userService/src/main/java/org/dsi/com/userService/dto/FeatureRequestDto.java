package org.dsi.com.userService.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureRequestDto {
    private String Name;
    private String status;
}
