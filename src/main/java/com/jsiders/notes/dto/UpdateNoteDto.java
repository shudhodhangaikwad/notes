package com.jsiders.notes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateNoteDto {
    @Min(value = 1, groups = {Update.class, Create.class},message = "ID is required")
    @NotNull(message = "ID is required")
    private Integer id;

    @NotEmpty(groups = {Create.class,Update.class},message = "Notes content is required")
    private String content;


}
