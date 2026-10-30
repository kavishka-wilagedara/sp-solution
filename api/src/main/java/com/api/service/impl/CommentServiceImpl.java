package com.api.service.impl;

import com.api.dto.request.CommentRequest;
import com.api.dto.response.CommentResponse;
import com.api.exception.NewsNotFoundException;
import com.api.mapper.CommentMapper;
import com.api.model.Comment;
import com.api.model.User;
import com.api.repository.CommentRepository;
import com.api.repository.NewsRepository;
import com.api.repository.UserRepository;
import com.api.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              UserRepository userRepository,
                              NewsRepository newsRepository,
                              CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentResponse addComment(Long newsId, CommentRequest commentRequest) {
        checkNewsExists(newsId);

        String name = commentRequest.name().trim();
        User user = userRepository.save(newUser(name));

        Comment comment = Comment.builder()
                .newsId(newsId)
                .name(name)
                .content(commentRequest.content())
                .build();

        return commentMapper.toResponse(commentRepository.save(comment));
    }

    @Override
    public List<CommentResponse> getComments(Long newsId) {
        checkNewsExists(newsId);
        return commentRepository.findByNewsId(newsId)
                .stream()
                .map(commentMapper::toResponse)
                .toList();
    }

    private void checkNewsExists(Long newsId) {
        newsRepository.findByIdAndIsPublishedTrue(newsId)
                .orElseThrow(() -> new NewsNotFoundException("News not found with ID: " + newsId));
    }

    private User newUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }
}
