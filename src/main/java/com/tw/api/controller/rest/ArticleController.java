package com.tw.api.controller.rest;

import com.tw.api.contract.ArticleRequest;
import com.tw.api.contract.ArticleResponse;
import com.tw.api.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/${api.version}")
@Api(value = "Article REST API", description = "Article related API")
public class ArticleController {

    private ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @ApiOperation(value = "POST", notes = "Create an article")
    @RequestMapping(value = "/{authorId}/article", method = RequestMethod.POST)
    public ResponseEntity<ArticleResponse> createArticle(@PathVariable String authorId,
                                                        @RequestBody ArticleRequest articleRequest) {
        ArticleResponse articleResponse = service.createArticle(authorId, articleRequest);
        return ResponseEntity.status(CREATED).body(articleResponse);
    }

    @ApiOperation(value = "GET", notes = "Find an article with article id")
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public ResponseEntity<ArticleResponse> findArticleWithId(@PathVariable String articleId) {
        ArticleResponse articleResponse = service.findArticle(articleId);
        HttpStatus status = articleResponse == null ? INTERNAL_SERVER_ERROR : OK;
        return ResponseEntity.status(status).body(articleResponse);
    }

    @ApiOperation(value = "GET", notes = "Find all articles")
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleResponse>> findArticles() {
        List<ArticleResponse> allArticles = service.findAllArticles();
        return ResponseEntity.status(OK).body(allArticles);
    }

    @ApiOperation(value = "GET", notes = "Find all articles with author id")
    @RequestMapping(value = "/{authorId}/articles", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleResponse>> findArticlesWithinAuthor(@PathVariable String authorId) {
        List<ArticleResponse> allArticlesWithinAuthor = service.findArticles(authorId);
        return ResponseEntity.status(OK).body(allArticlesWithinAuthor);
    }

    @ApiOperation(value = "PUT", notes = "Update an article")
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.PUT)
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable String articleId,
                                                       @RequestBody ArticleRequest articleRequest) {
        ArticleResponse articleResponse = service.updateArticle(articleId, articleRequest);
        return ResponseEntity.status(OK).body(articleResponse);
    }

    @ApiOperation(value = "PUT", notes = "Update an article with increasing page view")
    @RequestMapping(value = "/article/{articleId}/pageview", method = RequestMethod.PUT)
    public ResponseEntity<ArticleResponse> updatePageView(@PathVariable String articleId) {
        ArticleResponse articleResponse = service.increasePageView(articleId);
        return ResponseEntity.status(OK).body(articleResponse);
    }

    @ApiOperation(value = "DELETE", notes = "Delete an article")
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteArticle(@PathVariable String articleId) {
        service.deleteArticle(articleId);
        return ResponseEntity.status(OK).body("success");
    }

    @ApiOperation(value = "DELETE", notes = "Delete all articles with in author")
    @RequestMapping(value = "/{authorId}/articles", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteArticlesWithinAuthor(@PathVariable String authorId) {
        service.deleteArticlesWithinAuthor(authorId);
        return ResponseEntity.status(OK).body("success");
    }
}
