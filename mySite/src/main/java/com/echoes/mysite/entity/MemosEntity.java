package com.echoes.mysite.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "memos")
public class MemosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "content")
    private String content;

    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "is_delete")
    private Boolean isDelete;
}
