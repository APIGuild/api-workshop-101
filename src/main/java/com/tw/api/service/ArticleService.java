package com.tw.api.service;

import com.tw.api.contract.ArticleRequest;
import com.tw.api.contract.ArticleResponse;
import com.tw.api.contract.AuthorRequest;
import com.tw.api.contract.AuthorResponse;
import com.tw.api.model.Article;
import com.tw.api.model.Author;
import com.tw.api.repository.ArticleRepository;
import com.tw.api.repository.AuthorRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private Mapper mapper;
    private AuthorRepository authorRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(Mapper mapper, AuthorRepository authorRepository,
                          ArticleRepository articleRepository) {
        this.mapper = mapper;
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
    }

    public ArticleResponse createArticle(String authorId, ArticleRequest articleRequest) {
        Author author = authorRepository.find(authorId);
        Article article = mapper.map(articleRequest, Article.class);
        article.setAuthor(author);
        articleRepository.save(article);
        return mapper.map(article, ArticleResponse.class);
    }

    public ArticleResponse findArticle(String articleId) {
        Article article = articleRepository.find(articleId);
        return mapper.map(article, ArticleResponse.class);
    }

    public List<ArticleResponse> findAllArticles() {
        return articleRepository.findAll().stream()
                .map(article -> mapper.map(article, ArticleResponse.class))
                .collect(Collectors.toList());
    }

    public List<ArticleResponse> findArticles(String authorId) {
        return articleRepository.findAll().stream()
                .filter(article -> authorId.equals(article.getAuthor().getId()))
                .map(article -> mapper.map(article, ArticleResponse.class))
                .collect(Collectors.toList());
    }

    public ArticleResponse updateArticle(String articleId, ArticleRequest articleRequest) {
        Article article = articleRepository.find(articleId);
        mapper.map(articleRequest, article);
        articleRepository.update(article);
        return mapper.map(article, ArticleResponse.class);
    }

    public void deleteArticle(String articleId) {
        articleRepository.delete(articleId);
    }

    public void deleteArticlesWithinAuthor(String authorId) {
        articleRepository.deleteArticlesWithinAuthor(authorId);
    }

    public ArticleResponse increasePageView(String articleId) {
        Article article = articleRepository.find(articleId);
        article.setPageView(article.getPageView() + 1);
        articleRepository.update(article);
        return mapper.map(article, ArticleResponse.class);
    }
}
