package com.api.mapper;

import com.api.dto.request.NewsRequest;
import com.api.dto.response.NewsResponse;
import com.api.model.News;
import com.api.model.NewsCategory;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public News toEntity(NewsRequest newsRequest, NewsCategory category){
        return News.builder()
                .newsCategory(category)
                .content(newsRequest.content())
                .author(newsRequest.author())
                .isPublished(true)
                .build();
    }

    public NewsResponse toResponse(News news){
        return new NewsResponse(
                news.getId(),
                news.getNewsCategory(),
                news.getContent(),
                news.getAuthor(),
                news.getUpdatedAt()
        );
    }
}
