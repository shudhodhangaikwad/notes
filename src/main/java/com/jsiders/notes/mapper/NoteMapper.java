package com.jsiders.notes.mapper;

import com.jsiders.notes.dto.NoteDto;
import com.jsiders.notes.entity.Note;

public class NoteMapper {

    private NoteMapper() {}

    public Note mapToNote(NoteDto noteDto,Note note) {
        note.setContent(noteDto.getContent());
        note.setUsername(noteDto.getUsername());
        return note;
    }

    public NoteDto mapToNoteDto(NoteDto noteDto,Note note) {
        noteDto.setContent(note.getContent());
        noteDto.setUsername(noteDto.getUsername());
        noteDto.setId(note.getId());
        noteDto.setCreatedAt(note.getCreatedAt());
        noteDto.setCreatedBy(note.getCreatedBy());
        noteDto.setUpdatedBy(note.getUpdatedBy());
        noteDto.setUpdatedAt(note.getUpdatedAt());
       // noteDto.setId(note.get);
        return noteDto;
    }

}
