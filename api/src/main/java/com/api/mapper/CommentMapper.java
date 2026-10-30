package com.api.mapper;

import com.api.dto.response.CommentResponse;
import com.api.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getNewsId(),
                comment.getName(),
                comment.getContent()
        );
    }
}
