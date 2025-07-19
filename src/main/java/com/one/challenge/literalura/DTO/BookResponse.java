package com.one.challenge.literalura.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResponse(
    @JsonAlias("title")
    String title,

    @JsonAlias("authors")
    List<AuthorResponse> authors,

    @JsonAlias("languages")
    List<String> languages,

    @JsonAlias("download_count")
    Integer downloadCount
) {
}
