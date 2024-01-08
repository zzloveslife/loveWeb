package com.echoes.mysite.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "hello")
public class HelloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hello")
    private String hello;
}
