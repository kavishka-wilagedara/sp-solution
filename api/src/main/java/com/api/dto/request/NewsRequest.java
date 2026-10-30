package com.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NewsRequest(

        @NotBlank(message = "'Category is required")
        String category,

        @NotBlank(message = "Content is required")
        String content,

        @NotBlank(message = "Author is required")
        String author
)   {}