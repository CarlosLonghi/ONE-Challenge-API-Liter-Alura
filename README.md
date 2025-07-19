# 📚 LiterAlura – API ONE Challenge

Este projeto é a solução de backend para o desafio **ONE Challenge Alura + Oracle**, usando **Java**, **Spring Boot**, **JPA** e **PostgreSQL** para criar uma aplicação de biblioteca que consome a API pública **Gutendex**, armazena livros e autores, e oferece diversas consultas por linha de comando.

## Funcionalidades

* 🎯 **Buscar livros por título** via API Gutendex e salvar localmente.
* 📦 **Listar livros cadastrados** no banco.
* 👤 **Listar autores cadastrados** no banco.
* 🌱 **Listar autores vivos em um determinado ano**.
* 🌐 **Listar livros por idioma**.

As opções acima são apresentadas em um menu interativo na linha de comando.

## 🛠 Tecnologias utilizadas

* Java 17+ (ou compatível)
* Spring Boot
* Spring Data JPA
* PostgreSQL
* API Gutendex (integração REST)
* Maven
* Docker

## 🚀 Instalação e execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/CarlosLonghi/ONE-Challenge-API-Liter-Alura.git
   cd ONE-Challenge-API-Liter-Alura
   ```

2. Suba o banco no *Docker* com o comando: `docker compose up -d`. E configure o banco de dados (PostgreSQL) nas variáveis `application.properties` (URL, usuário, senha, esquema).

3. Execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

4. Ao iniciar, você verá um menu numerado com opções, por exemplo:

   ```
   1 – Buscar livro por título (api Gutendex)
   2 – Listar livros cadastrados
   3 – Listar autores cadastrados
   4 – Listar autores vivos em X ano
   5 – Listar livros por idioma
   0 – Sair
   ```

## 📂 Estrutura do projeto

```
src/
└─ main/
   ├─ java/com/one/challenge/literalura/
   │    ├─ model/       → entidades Book, Author, BookResponse, AuthorResponse
   │    ├─ repository/  → BookRepository, AuthorRepository
   │    ├─ service/     → lógica de negócio
   │    └─ LiteraLuraApplication.java
   └─ resources/
        └─ application.properties
```

## 🧐 Detalhes de implementação

* **Busca e persistência**: integração com API Gutendex usando `RestTemplate`, mapeando objetos e salvando no banco.
* **Consulta autores vivos**: com JPQL

## 💡 Melhorias futuras

* Adicionar paginação (Pageable)
* Suporte a enums para idiomas
* Criar API REST para uso com frontend
* Validações e tratamento de erros mais robustos

## 💌 Execução de exemplo

```
> 1
Informe o título: Moby Dick
Livro salvo: "Moby Dick" (EN) — Autor(a): Melville, Herman

> 5
Informe o idioma (ex: EN, PT): EN
- Moby Dick — Melville, Herman
```

## 📧 Contato

Carlos Longhi — [GitHub](https://github.com/CarlosLonghi)

Projeto desenvolvido como parte do programa **ONE Challenge Alura & Oracle Back-End**.
