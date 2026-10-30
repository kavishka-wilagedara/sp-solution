package com.api.service;

import com.api.dto.request.NewsRequest;
import com.api.dto.response.NewsResponse;
import com.api.model.NewsCategory;

import java.util.List;

public interface NewsService {

    NewsResponse createNews(NewsRequest newsRequest);

    List<NewsResponse> getAllNews();

    NewsResponse getNews(Long newsId);
}
