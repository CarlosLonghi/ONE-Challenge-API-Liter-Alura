package com.one.challenge.literalura.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorResponse(
        String name,

        @JsonAlias("birth_year")
        Integer birthYear,

        @JsonAlias("death_year")
        Integer deathYear
) {
}
