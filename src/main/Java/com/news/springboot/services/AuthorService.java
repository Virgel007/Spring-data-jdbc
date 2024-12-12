package com.news.springboot.services;

import com.news.springboot.entity.Author;
import com.news.springboot.repositories.AuthorRepoJdbcTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class AuthorService {

    private final AuthorRepoJdbcTemplate authorRepositories;


    public Author getById(Integer id) {
        log.info("Get by ID: " + id);
        return authorRepositories.getById(id);
    }


    public List<Author> getAll() {
        log.info("Get all");
        return authorRepositories.getAll();
    }


    public void create(Author author) {
        log.info("Create");
        authorRepositories.create(author);
    }


    public void update(Integer id, Author author) {
        log.info("Update");
        authorRepositories.update(id, author);
    }


    public void delete(Integer id) {
        log.info("Delete " + id);
        authorRepositories.delete(id);
    }
}
