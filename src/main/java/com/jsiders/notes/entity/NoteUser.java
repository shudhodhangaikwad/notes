package com.jsiders.notes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
public class NoteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;



}
