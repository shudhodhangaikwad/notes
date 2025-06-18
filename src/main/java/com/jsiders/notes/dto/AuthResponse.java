package com.jsiders.notes.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AuthResponse {

    private String accessToken;

    private Date expiresIn;

    private String type = "Bearer";
}
