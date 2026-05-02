package com.example.demo.repository;

import com.example.demo.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {
    Book findByIsbn(String isbn);
    @Transactional
    @Modifying
    void deleteByIsbn(String isbn);

    boolean existsByIsbn(String isbn);
}
