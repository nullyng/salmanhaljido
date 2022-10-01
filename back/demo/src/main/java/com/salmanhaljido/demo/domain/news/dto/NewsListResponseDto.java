package com.salmanhaljido.demo.domain.news.dto;

import com.salmanhaljido.demo.domain.news.entity.News;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class NewsListResponseDto {
    List<NewsResponseDto> newsList;
    int totalCount;

    public static NewsListResponseDto of(Page<News> newsPage){
        List<NewsResponseDto> newsResponseDtoList = new ArrayList<>();

        newsPage.forEach((news) -> {
            newsResponseDtoList.add(NewsResponseDto.of(news));
        });

        return NewsListResponseDto.builder().newsList(newsResponseDtoList).totalCount(newsPage.getTotalPages()*8).build();
    }
}
