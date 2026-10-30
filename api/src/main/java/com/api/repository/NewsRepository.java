package com.api.repository;

import com.api.model.News;
import com.api.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByIsPublishedTrue();
    Optional<News> findByIdAndIsPublishedTrue(Long id);
}
