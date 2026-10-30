package com.api.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        Long newsId,
        String name,
        String content
) {}
