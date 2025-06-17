package com.jsiders.notes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PaginationResponse")
public class PaginationResponse<T> {
    private long total;
    private List<T> data;

}
