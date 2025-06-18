package com.jsiders.notes.repository;

import com.jsiders.notes.entity.NoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteUserRepository extends JpaRepository<NoteUser, Integer> {

    Optional<NoteUser> findByUsername(String username);

}
