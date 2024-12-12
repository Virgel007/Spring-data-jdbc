package com.news.springboot.services;

import com.news.springboot.entity.Author;
import com.news.springboot.repositories.AuthorRepositories;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class AuthorService implements CRUDService<Author> {

    private final AuthorRepositories authorRepositories;

    @Override
    public Author getById(Integer id) {
        log.info("Get by ID: " + id);
        return authorRepositories.findById(id).orElseThrow();
    }

    @Override
    public List<Author> getAll() {
        log.info("Get all");
        return authorRepositories.findAll();
    }

    @Override
    public void create(Author author) {
        log.info("Create");
        authorRepositories.save(author);
    }

    @Override
    public void update(Integer id, Author author) {
        log.info("Update");
        authorRepositories.save(author);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        authorRepositories.deleteById(id);
    }
}
