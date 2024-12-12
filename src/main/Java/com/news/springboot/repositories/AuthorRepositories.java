package com.news.springboot.repositories;

import com.news.springboot.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositories extends JpaRepository<Author, Integer> {
}
