package com.api.service.impl;

import com.api.dto.request.NewsRequest;
import com.api.dto.response.NewsResponse;
import com.api.exception.NewsNotFoundException;
import com.api.mapper.NewsMapper;
import com.api.model.News;
import com.api.model.NewsCategory;
import com.api.repository.NewsRepository;
import com.api.service.NewsService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private  final NewsMapper newsMapper;

    public NewsServiceImpl(
            NewsRepository newsRepository,
            NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    @Override
    public NewsResponse createNews(NewsRequest newsRequest) {
        NewsCategory category = parseCategory(newsRequest.category());
        News news = newsMapper.toEntity(newsRequest, category);
        News savedNews = newsRepository.save(news);

        return newsMapper.toResponse(savedNews);
    }

    @Override
    public List<NewsResponse> getAllNews() {
        return newsRepository.findByIsPublishedTrue()
                .stream()
                .map(newsMapper::toResponse)
                .toList();
    }

    @Override
    public NewsResponse getNews(Long newsId) {
        News news = newsRepository.findByIdAndIsPublishedTrue(newsId)
                .orElseThrow(() -> new NewsNotFoundException("News not found with ID " + newsId));

        return newsMapper.toResponse(news);
    }

    // Search category validation
    private NewsCategory parseCategory(String category){
        try {
            return NewsCategory.valueOf(category.trim().toUpperCase());
        }
        catch (IllegalArgumentException |NullPointerException e) {
            throw new IllegalArgumentException("Invalid category " + category+ "");
        }
    }
}