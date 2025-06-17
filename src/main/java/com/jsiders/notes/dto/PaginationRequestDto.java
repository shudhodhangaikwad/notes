package com.jsiders.notes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(title = "PaginationRequest")
@Data
public class PaginationRequestDto {

    @Min(value = 1,message = "Page number is missing")
    @NotNull(message = "Page number is required")
    private Integer page = 1;

    @Min(value = 20,message = "Page size is missing")
    @NotNull(message = "Page size is required")
    private Integer size = 20;

    private String orderBy = "id";

    @Schema(allowableValues = {"asc","desc"})
    private String order = "desc";

    @NotEmpty(message = "User name is required")
    private String username;

}
