package com.tw.api.repository;

import com.tw.api.model.Article;
import com.tw.api.model.Author;
import com.tw.api.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ArticleRepository {

    private Map<String, Article> articleMap = new HashMap<>();

    private UUIDGenerator generator;

    @Autowired
    public ArticleRepository(UUIDGenerator generator) {
        this.generator = generator;
    }

    public void save(Article article) {
        article.setId(generator.generate());
        article.setPageView(0);
        articleMap.put(article.getId(), article);
    }

    public Article find(String articleId) {
        return articleMap.get(articleId);
    }

    public Collection<Article> findAll() {
        return articleMap.values();
    }

    public void update(Article article) {
        articleMap.put(article.getId(), article);
    }

    public void delete(String articleId) {
        articleMap.remove(articleId);
    }

    public void update(Author author) {
        articleMap.values().stream()
                .filter(article -> author.getId().equals(article.getAuthor().getId()))
                .forEach(article -> article.setAuthor(author));
    }

    public void deleteArticlesWithinAuthor(String authorId) {
        findAll().stream()
                .filter(article -> authorId.equals(article.getAuthor().getId()))
                .forEach(article -> delete(article.getId()));
    }
}
