package com.jsiders.notes.sevice.impl;

import com.jsiders.notes.dto.NoteDto;
import com.jsiders.notes.dto.PaginationRequestDto;
import com.jsiders.notes.dto.PaginationResponse;
import com.jsiders.notes.entity.Note;
import com.jsiders.notes.exceptions.ResourceNotFoundException;
import com.jsiders.notes.repository.NoteRepository;
import com.jsiders.notes.sevice.INoteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService implements INoteService {

    private NoteRepository noteRepository;

    private ModelMapper modelMapper;

    @Override
    public NoteDto createNote(NoteDto noteDto) {
        Note note = noteRepository.save(modelMapper.map(noteDto, Note.class));
        return modelMapper.map(note, NoteDto.class);
    }

    @Override
    public NoteDto updateNote(NoteDto noteDto) {
        Note note = noteRepository.findById(noteDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + noteDto.getId()));
        note.setContent(noteDto.getContent());
        note = noteRepository.save(note);
        return modelMapper.map(note, NoteDto.class);
    }

    @Override
    public NoteDto deleteNote(Integer noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + noteId));
        noteRepository.delete(note);
        return modelMapper.map(note, NoteDto.class);
    }

    @Override
    public PaginationResponse<NoteDto> getNotes(PaginationRequestDto request) {
        Sort sort = Sort.by(Sort.Direction.fromString(request.getOrder()), request.getOrderBy());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<Note> page;
        if (StringUtils.hasText(request.getUsername())) {
            page = noteRepository.findByUsername(request.getUsername(), pageable);
        } else
            page = noteRepository.findAll(pageable);
        List<NoteDto> notes = page.getContent()
                .stream()
                .map(note -> modelMapper.map(note, NoteDto.class))
                .toList();
        long total = page.getTotalElements();
        return new PaginationResponse<>(total, notes);
    }
}
