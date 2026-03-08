package com.literatura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {

    @JsonAlias("results")
    private List<BookData> results;

    public List<BookData> getResults() {
        return results;
    }

    public void setResults(List<BookData> results) {
        this.results = results;
    }
}