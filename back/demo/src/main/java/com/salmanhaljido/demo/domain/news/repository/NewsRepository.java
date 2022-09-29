package com.salmanhaljido.demo.domain.news.repository;

import com.salmanhaljido.demo.domain.news.entity.Category;
import com.salmanhaljido.demo.domain.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAllByCategory(Category category, Pageable pageable);
    @Query(value = "SELECT * FROM news n WHERE n.category = ?1 And (n.title LIKE '%2%' OR n.summary LIKE '%2%' OR n.title LIKE '%7%' OR n.summary LIKE '%7%') ORDER BY id"
            ,countQuery = "SELECT COUNT(*) FROM news",
            nativeQuery = true)
    Page<News> findAllByCategoryOrTitleOrSummaryWithPagination(Category category, String sido, String sigungu, Pageable pageable);
}
