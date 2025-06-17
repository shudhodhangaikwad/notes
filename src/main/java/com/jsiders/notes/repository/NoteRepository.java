package com.jsiders.notes.repository;

import com.jsiders.notes.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    Page<Note> findByUsername(String username, Pageable pageable);
    List<Note> findByUsername(String username);

}


