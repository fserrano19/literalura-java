package com.literatura.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literatura.literalura.dto.GutendexResponse;

public class JsonParserService {

    private ObjectMapper mapper = new ObjectMapper();

    public GutendexResponse parseJson(String json) {

        try {
            return mapper.readValue(json, GutendexResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }

    }
}