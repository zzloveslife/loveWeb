package com.echoes.mysite.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "photo_board")
public class PhotoBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "content")
    private String content;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "is_delete")
    private Boolean isDelete;
}
