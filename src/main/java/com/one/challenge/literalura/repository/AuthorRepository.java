package com.one.challenge.literalura.repository;

import com.one.challenge.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Lista autores pelo nome
    Author findByNameIgnoreCase(String name);

    /**
     * Lista todos os autores que já nasceram até o ano informado
     * e que ou não têm ano de morte (ainda vivos) ou morreram depois/desse ano.
     * */
    @Query("""
       SELECT a
         FROM Author a
        WHERE a.birthYear <= :year
          AND (a.deathYear IS NULL OR a.deathYear >= :year)
       """)
    List<Author> findAuthorsAliveInYear(@Param("year") Integer year);
}
