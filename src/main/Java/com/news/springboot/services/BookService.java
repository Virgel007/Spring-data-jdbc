package com.news.springboot.services;

import com.news.springboot.entity.Book;
import com.news.springboot.repositories.BookRepositories;
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

    private final BookRepositories bookRepositories;

    public Page<Book> getAllPaged(Pageable pageable) {
        return bookRepositories.findAll(pageable);
    }

    public Optional<Book> getById(Integer id) {
        return bookRepositories.findById(id);
    }

    public Book create(Book book) {
        return bookRepositories.save(book);
    }

    public Optional<Book> update(Integer id, Book book) {
        if (bookRepositories.existsById(id)) {
            Book entity = bookRepositories.getById(id);
            entity.setTitle(book.getTitle());
            entity.setAuthor(book.getAuthor());
            return Optional.of(bookRepositories.save(entity));
        }
        return Optional.empty();
    }

    public void delete(Integer id) {
        bookRepositories.deleteById(id);
    }

}