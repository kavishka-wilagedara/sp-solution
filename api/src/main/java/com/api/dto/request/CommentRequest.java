package com.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequest(

        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Comment content is required")
        String content
) {}
