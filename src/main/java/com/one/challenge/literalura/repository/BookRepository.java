package com.one.challenge.literalura.repository;

import com.one.challenge.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Lista livros de um determinado idioma
    List<Book> findByLanguageIgnoreCase(String language);
}
