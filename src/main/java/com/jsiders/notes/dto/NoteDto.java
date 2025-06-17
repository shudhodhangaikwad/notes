package com.jsiders.notes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteDto {

    @Min(value = 1, groups = {Update.class, Create.class},message = "ID is required")
    @NotNull(message = "Id is missing")
    private Integer id;

    @NotEmpty(groups = {Create.class,Update.class},message = "Notes content is required")
    private String content;

    @NotEmpty(groups = {Create.class},message = "Notes username is required")
    private String username;

    @Schema(hidden = true)
    private LocalDateTime createdAt;
    @Schema(hidden = true)
    private String createdBy;
    @Schema(hidden = true)
    private LocalDateTime updatedAt;
    @Schema(hidden = true)
    private String updatedBy;
}
