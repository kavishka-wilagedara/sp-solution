package com.api.service;

import com.api.dto.request.CommentRequest;
import com.api.dto.request.NewsRequest;
import com.api.dto.response.CommentResponse;
import com.api.dto.response.NewsResponse;

import java.util.List;

public interface CommentService {
    CommentResponse addComment(Long newsId, CommentRequest commentRequest);
    List<CommentResponse> getComments(Long newsId);
}
