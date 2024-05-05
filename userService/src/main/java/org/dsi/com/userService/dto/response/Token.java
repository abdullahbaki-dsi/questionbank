package org.dsi.com.userService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Data
public class Token {
    private String accessToken;
    private String refreshToken;
    private String type;
}
