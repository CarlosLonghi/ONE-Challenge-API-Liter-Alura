package com.one.challenge.literalura.main;

import com.one.challenge.literalura.DTO.AuthorResponse;
import com.one.challenge.literalura.DTO.BookResponse;
import com.one.challenge.literalura.DTO.Response;
import com.one.challenge.literalura.model.Author;
import com.one.challenge.literalura.model.Book;
import com.one.challenge.literalura.repository.AuthorRepository;
import com.one.challenge.literalura.repository.BookRepository;
import com.one.challenge.literalura.service.DataConvert;
import com.one.challenge.literalura.service.RequestAPI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class Main {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final AuthorRepository authorRepository;

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private static final String URL_BASE = "https://gutendex.com/books/";
    private RequestAPI requestAPI = new RequestAPI();
    private DataConvert dataConvert = new DataConvert();
    private String json;

    private Scanner scanner = new Scanner(System.in);

    private String menu = """
            -----------------------------------
            Escolha sua opção digitando um número:
            
            1 - Buscar/Registrar livro pelo título
            2 - Listar livros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos em determinado ano
            5 - Listar livros de um determinado idioma
            0 - SAIR """;

    public void renderMenu() {
        int selectedOption = - 1;

        while (selectedOption != 0) {
            json = requestAPI.getData(URL_BASE);

            System.out.println(menu);
            selectedOption = scanner.nextInt();
            scanner.nextLine();

            switch (selectedOption) {
                case 0: {
                    System.out.println("Saindo da Aplicação...");
                    scanner.close();
                    break;
                }
                case 1: {
                    findBookByTitle();
                    break;
                }
                case 2: {
                    List<Book> books = bookRepository.findAll();
                    System.out.println("Livros Cadastrados:");
                    books.forEach(System.out::println);
                    break;
                }
                case 3: {
                    List<Author> authors = authorRepository.findAll();
                    System.out.println("Autores Cadastrados:");
                    authors.forEach(System.out::println);
                    break;
                }
                case 4: {
                    System.out.println("Digite um ano para buscar:");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    List<Author> authorsAliveInYear = authorRepository.findAuthorsAliveInYear(year);
                    authorsAliveInYear.forEach(System.out::println);
                    break;
                }
                case 5: {
                    System.out.println("""
                            Digite um número para escolher um idioma:
                            
                            1 - PT (Português)
                            2 - EN (Inglês)
                            3 - FR (Frances) """);
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    String languageToFilter = "EN";

                    switch (choice) {
                        case 1 -> languageToFilter = "PT";
                        case 2 -> languageToFilter = "EN";
                        case 3 -> languageToFilter = "FR";
                        default -> System.out.println("Opção inválida!");
                    }

                    List<Book> booksByLanguage = bookRepository.findByLanguageIgnoreCase(languageToFilter);

                    if (booksByLanguage.isEmpty()) {
                        System.out.println("Nenhum livro encontrado dessa linguagem");
                        break;
                    }

                    booksByLanguage.forEach(System.out::println);

                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                }
            }
        }
    }

    private void findBookByTitle() {
        BookResponse bookResponse = getBookData();

        if (bookResponse == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        Book book;
        AuthorResponse authorResponse = bookResponse.authors().getFirst();
        Author author = authorRepository.findByNameIgnoreCase(authorResponse.name());

        if (author != null) {
            book = new Book(bookResponse, author);
        } else {
            Author newAuthor = new Author(authorResponse);
            authorRepository.save(newAuthor);
            book = new Book(bookResponse, newAuthor);
        }

        try {
            bookRepository.save(book);
            System.out.println(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private BookResponse getBookData() {
        System.out.println("Digite o nome do livro: ");
        String bookTitle = scanner.nextLine();

        json = requestAPI.getData(URL_BASE + "?search=" + bookTitle.replace(" ", "+"));

        Response response = dataConvert.getData(json, Response.class);

        return response.books().stream()
                .filter(book -> book.title().contains(bookTitle))
                .findFirst()
                .orElse(null);
    }
}
