package com.news.springboot.controllers;

import com.news.springboot.entity.Author;
import com.news.springboot.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/v1/authors")
@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        authorService.create(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Integer id) {
        Author author = authorService.getById(id);
        if (author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with id = " + id + " not found");
        }
        return ResponseEntity.ok(author);
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> allAuthors = authorService.getAll();
        if (allAuthors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(allAuthors);
        }
        return ResponseEntity.ok(allAuthors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer id) {
        Author author = authorService.getById(id);
        if (author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with id = " + id + " not found");
        }
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Author with id = " + id + " deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
        Author existingAuthor = authorService.getById(id);
        if (existingAuthor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with id = " + id + " not found");
        }
        authorService.update(id, author);
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }
}