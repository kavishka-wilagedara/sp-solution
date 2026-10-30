package com.api.repository;

import com.api.dto.response.CommentResponse;
import com.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByNewsId(Long newsId);
}
