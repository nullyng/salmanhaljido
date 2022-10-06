package com.salmanhaljido.demo.domain.news.controller;

import com.salmanhaljido.demo.domain.news.dto.NewsListResponseDto;
import com.salmanhaljido.demo.domain.news.entity.Category;
import com.salmanhaljido.demo.domain.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;

@RestController
@RequestMapping(("/boards"))
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("{categories}")
    public ResponseEntity<NewsListResponseDto> getNews(@PathVariable("categories") Category categories, @RequestParam("pageNo") int pageNo, @RequestParam("search") @Nullable String search){
        return ResponseEntity.ok().body(newsService.getNews(categories, pageNo, search));
    }
}
