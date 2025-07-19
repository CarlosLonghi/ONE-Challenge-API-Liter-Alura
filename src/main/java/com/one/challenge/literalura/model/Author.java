package com.one.challenge.literalura.model;

import com.one.challenge.literalura.DTO.AuthorResponse;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    public Author() {
    }

    public Author(AuthorResponse authorResponse) {
        this.name = authorResponse.name();
        this.birthYear = authorResponse.birthYear();
        this.deathYear = authorResponse.deathYear();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private List<String> getAllBookTitles() {
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "--------------------------------------------------" +
                "\n Nome: " + name +
                "\n Ano de nascimento: " + birthYear +
                "\n Ano de falecimento: " + deathYear +
                "\n Livros: " + getAllBookTitles();
    }
}
