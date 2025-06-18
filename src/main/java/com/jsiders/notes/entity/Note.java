package com.jsiders.notes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Note extends BaseEntity{
    //@Lob working only with MySQL
    private String content;

    @Column(nullable = false)
    private String username;

}
