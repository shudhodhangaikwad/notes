package com.jsiders.notes.controller;

import com.jsiders.notes.dto.*;
import com.jsiders.notes.sevice.INoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/note")
@AllArgsConstructor
@Tag(name = "Notes", description = "CRUD operation on Notes")
public class NoteController {

    private INoteService noteService;


    @Operation(
            summary = "Create Note",
            description = "Create a note",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Note created successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),

            }
    )
    @PostMapping
    public ResponseEntity<NoteDto> saveNote(@Validated(Create.class) @RequestBody NoteDto noteDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noteService.createNote(noteDto));
    }


    @Operation(
            summary = "List notes",
            description = "List notes with pagination",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @GetMapping
    public ResponseEntity<PaginationResponse<NoteDto>> findAll(@ModelAttribute @ParameterObject PaginationRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noteService.getNotes(requestDto));
    }


    @Operation(
            summary = "Delete notes",
            description = "Delete note by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Deleted"),
                    @ApiResponse(responseCode = "400", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<NoteDto> deleteNote(@Min(value = 1, message = "Invalid id") @PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noteService.deleteNote(id));
    }

    @Operation(
            summary = "Update Note",
            description = "Update notes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update success"),
                    @ApiResponse(responseCode = "400", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @PutMapping
    public ResponseEntity<NoteDto> updateNote(@Valid @ModelAttribute @ParameterObject UpdateNoteDto updateNoteDto) {
        NoteDto noteDto=new NoteDto();
        noteDto.setId(updateNoteDto.getId());
        noteDto.setContent(updateNoteDto.getContent());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noteService.updateNote(noteDto));
    }


}
