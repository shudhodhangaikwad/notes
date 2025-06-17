package com.jsiders.notes.sevice;

import com.jsiders.notes.dto.NoteDto;
import com.jsiders.notes.dto.PaginationRequestDto;
import com.jsiders.notes.dto.PaginationResponse;

public interface INoteService {

    NoteDto createNote(NoteDto note);

    NoteDto updateNote(NoteDto note);

    NoteDto deleteNote(Integer noteId);
    PaginationResponse<NoteDto> getNotes(PaginationRequestDto request);

}
