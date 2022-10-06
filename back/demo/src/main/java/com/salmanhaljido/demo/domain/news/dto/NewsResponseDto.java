package com.salmanhaljido.demo.domain.news.dto;

import com.salmanhaljido.demo.domain.news.entity.News;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class NewsResponseDto {
    private String title;
    private Date createdAt;
    private String summary;
    private String url;
    private String imageURL;

    public static NewsResponseDto of(News entity){
        return NewsResponseDto.builder().title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .summary(entity.getSummary())
                .url(entity.getUrl())
                .imageURL(entity.getImagePath())
                .build();
    }
}
