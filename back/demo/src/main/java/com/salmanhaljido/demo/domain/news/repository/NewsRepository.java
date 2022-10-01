package com.salmanhaljido.demo.domain.news.repository;

import com.salmanhaljido.demo.domain.news.entity.Category;
import com.salmanhaljido.demo.domain.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAllByCategory(Category category, Pageable pageable);
    @Query(value = "SELECT * FROM news n WHERE n.category = :category And (n.title LIKE :sido OR n.summary LIKE :sido OR n.title LIKE :sigungu OR n.summary LIKE :sigungu) ORDER BY n.id"
            ,countQuery = "SELECT COUNT(n.id) as col FROM news n WHERE category = :category",
            nativeQuery = true)
    Page<News> findAllByCategoryOrTitleOrSummaryWithPagination(@Param("category") String category,@Param("sido") String sido,@Param("sigungu") String sigungu, Pageable pageable);
}
