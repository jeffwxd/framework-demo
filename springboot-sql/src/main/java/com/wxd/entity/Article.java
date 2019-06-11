package com.wxd.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "comment_count")
    private Long commentCount;

    private Long version;
}
