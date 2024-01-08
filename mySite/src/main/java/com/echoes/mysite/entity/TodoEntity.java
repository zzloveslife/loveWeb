package com.echoes.mysite.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "todo")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "content")
    private String content;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "has_done")
    private Boolean hasDone;
}
