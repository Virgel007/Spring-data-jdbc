package com.news.springboot.repositories;

import com.news.springboot.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepoJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;


    public List<Author> getAll() {
        return jdbcTemplate.query("SELECT * FROM authors", (rs, rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            return author;
        });
    }

    public Author getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM authors WHERE id = ?", new Object[]{id}, (rs, rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            return author;
        });
    }

    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO authors (name) VALUES (?)", author.getName());
    }

    public void update(Integer id, Author author) {
        jdbcTemplate.update("UPDATE authors SET name = ? WHERE id = ?", author.getName(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM authors WHERE id = ?", id);
    }

    public List<Author> findAuthorsByBook(String bookTitle) {
        String sql = "SELECT a.* FROM authors a JOIN books b ON a.id = b.author_id WHERE b.title = ?";
        return jdbcTemplate.query(sql, new Object[]{bookTitle}, (rs, rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            return author;
        });
    }
}