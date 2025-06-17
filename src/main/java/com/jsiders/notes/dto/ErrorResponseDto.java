package com.jsiders.notes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "ErrorResponse",description = "Returned for 400,500 http errors")
@Data
public class ErrorResponseDto {

    private int statusCode;
    private String message;
    private List<String> errors;

}
