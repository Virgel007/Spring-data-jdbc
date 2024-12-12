package com.news.springboot.services;


import com.news.springboot.entity.Book;
import com.news.springboot.repositories.BookRepoJdbcTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookService {

    private final BookRepoJdbcTemplate bookRepositories;

    public Page<Book> getAllPaged(Pageable pageable) {
        return bookRepositories.getAllPaged(pageable);
    }

    public Optional<Book> getById(Integer id) {
        Book book = bookRepositories.getById(id);
        return Optional.ofNullable(book);
    }

    public void create(Book book) {
        bookRepositories.create(book);
    }

    public Optional<Book> update(Integer id, Book book) {
            Book book1 = bookRepositories.getById(id);
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            bookRepositories.create(book1);
            return Optional.of(book1);
    }

    public void delete(Integer id) {
        bookRepositories.delete(id);
    }
}