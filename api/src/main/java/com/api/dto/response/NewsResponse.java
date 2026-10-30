package com.api.dto.response;

import com.api.model.NewsCategory;

import java.time.LocalDateTime;

public record NewsResponse(

        Long id,
        NewsCategory category,
        String content,
        String author,
        LocalDateTime updatedAt
) {}
