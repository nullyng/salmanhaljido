package com.salmanhaljido.demo.domain.news.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String summary;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private String url;

    private String imagePath;

    private Date createdAt;

    @Builder
    public News(String title, String summary, Category category, String url, String imagePath, Date createdAt) {
        this.title = title;
        this.summary = summary;
        this.category = category;
        this.url = url;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }
}
