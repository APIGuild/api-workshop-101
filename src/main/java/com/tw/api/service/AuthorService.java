package com.tw.api.service;

import com.tw.api.contract.AuthorRequest;
import com.tw.api.contract.AuthorResponse;
import com.tw.api.model.Author;
import com.tw.api.repository.ArticleRepository;
import com.tw.api.repository.AuthorRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private Mapper mapper;
    private AuthorRepository authorRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public AuthorService(Mapper mapper, AuthorRepository authorRepository,
                         ArticleRepository articleRepository) {
        this.mapper = mapper;
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
    }

    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        Author author = mapper.map(authorRequest, Author.class);
        authorRepository.save(author);
        return mapper.map(author, AuthorResponse.class);
    }

    public AuthorResponse findAuthor(String authorId) {
        Author author = authorRepository.find(authorId);
        return mapper.map(author, AuthorResponse.class);
    }

    public List<AuthorResponse> findAllAuthor() {
        return authorRepository.findAll().stream()
                .map(author -> mapper.map(author, AuthorResponse.class))
                .collect(Collectors.toList());
    }

    public AuthorResponse updateAuthor(String authorId, AuthorRequest authorRequest) {
        Author author = authorRepository.find(authorId);
        mapper.map(authorRequest, author);
        authorRepository.update(author);
        articleRepository.update(author);
        return mapper.map(author, AuthorResponse.class);
    }

    public void deleteAuthor(String authorId) {
        authorRepository.delete(authorId);
        articleRepository.deleteArticlesWithinAuthor(authorId);
    }
}
