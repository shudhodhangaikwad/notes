package com.jsiders.notes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String accessToken;

    private Long expiresIn;

    private String type = "Bearer";
}
