package com.tw.api.service;

import com.tw.api.contract.AuthorRequest;
import com.tw.api.contract.AuthorResponse;
import com.tw.api.model.Author;
import com.tw.api.repository.AuthorRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private Mapper mapper;
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(Mapper mapper, AuthorRepository authorRepository) {
        this.mapper = mapper;
        this.authorRepository = authorRepository;
    }

    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        Author author = mapper.map(authorRequest, Author.class);
        authorRepository.save(author);
        return mapper.map(author, AuthorResponse.class);
    }
}
