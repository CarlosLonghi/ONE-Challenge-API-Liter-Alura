package com.one.challenge.literalura.model;

import com.one.challenge.literalura.DTO.BookResponse;
import jakarta.persistence.*;

@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String language;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "download_count")
    private Integer downloadCount;

    public Book() {
    }

    public Book(BookResponse bookResponse, Author author) {
        this.title = bookResponse.title();
        this.author = author;
        this.language = bookResponse.languages().getFirst();
        this.downloadCount = bookResponse.downloadCount();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public Author getAuthor() {
        return author;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    @Override
    public String toString() {
        return "-----------------------------------------------" +
                "\nLivro: " + title +
                "\nIdioma: " + language +
                "\nAutor: " + author +
                "\nNúmero de Downloads: " + downloadCount;
    }
}
