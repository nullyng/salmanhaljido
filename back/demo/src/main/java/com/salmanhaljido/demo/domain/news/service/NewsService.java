package com.salmanhaljido.demo.domain.news.service;

import com.salmanhaljido.demo.domain.news.dto.NewsListResponseDto;
import com.salmanhaljido.demo.domain.news.entity.Category;

public interface NewsService {
    NewsListResponseDto getNews(Category category, int pageNo);
    void crawlingParentingAndEducation();
}
