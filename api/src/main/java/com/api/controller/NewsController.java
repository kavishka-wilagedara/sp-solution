package com.api.controller;

import com.api.dto.request.NewsRequest;
import com.api.dto.response.NewsResponse;
import com.api.service.NewsService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public ResponseEntity<NewsResponse> createNews(
            @Valid @RequestBody NewsRequest newsRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsService.createNews(newsRequest));
    }

    @GetMapping
    public ResponseEntity<List<NewsResponse>> getNews(){
        List<NewsResponse> result = newsService.getAllNews();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{newsId}")
    public ResponseEntity<NewsResponse> getNewsById(@PathVariable Long newsId) {
        return ResponseEntity.ok(newsService.getNews(newsId));
    }

}
