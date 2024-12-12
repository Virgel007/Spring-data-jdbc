package com.news.springboot.repositories;

import com.news.springboot.entity.Author;
import com.news.springboot.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepoJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    public Page<Book> getAllPaged(Pageable pageable) {
        int total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM books", Integer.class);
        List<Book> books = jdbcTemplate.query("SELECT * FROM books LIMIT ? OFFSET ?",
                new Object[]{pageable.getPageSize(), pageable.getOffset()}, (rs, rowNum) -> {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(findAuthorsByBook(book.getTitle()).get(0));
                    return book;
                });
        return new PageImpl<>(books, pageable, total);
    }

    public Book getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id = ?", new Object[]{id}, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(findAuthorsByBook(book.getTitle()).get(0));
            return book;
        });
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO books (title, author_id) VALUES (?, ?)", book.getTitle(), book.getAuthor().getId());
    }

    public void update(Integer id, Book book) {
        jdbcTemplate.update("UPDATE books SET title = ?, author_id = ? WHERE id = ?", book.getTitle(), book.getAuthor().getId(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    private List<Author> findAuthorsByBook(String title) {
        return jdbcTemplate.query("SELECT * FROM authors WHERE id IN (SELECT author_id FROM books WHERE title = ?)", new Object[]{title},
                (rs, rowNum) -> {
                    Author author = new Author();
                    author.setId(rs.getInt("id"));
                    author.setName(rs.getString("author_name"));
                    return author;
                });
    }
}