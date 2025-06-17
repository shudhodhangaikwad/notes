package com.jsiders.notes.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(updatable = false,name = "created_at")
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false,name = "created_by")
    private String createdBy;


    @LastModifiedDate
    @Column(insertable = false ,name = "updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false,name = "updated_by")
    private String updatedBy;


}
