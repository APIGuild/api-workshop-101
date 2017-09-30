package com.tw.api.repository;

import com.tw.api.model.Author;
import com.tw.api.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorRepository {

    private Map<String, Author> authorMap = new HashMap<>();

    private UUIDGenerator generator;

    @Autowired
    public AuthorRepository(UUIDGenerator generator) {
        this.generator = generator;
    }

    public void save(Author author) {
        author.setId(generator.generate());
        authorMap.put(author.getId(), author);
    }

    public Author find(String authorId) {
        return authorMap.get(authorId);
    }

    public Collection<Author> findAll() {
        return authorMap.values();
    }

    public void update(Author author) {
        authorMap.put(author.getId(), author);
    }

    public void delete(String authorId) {
        authorMap.remove(authorId);
    }
}
