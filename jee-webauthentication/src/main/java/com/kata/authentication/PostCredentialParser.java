package com.kata.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

class PostCredentialParser implements RequestCredentialParser {

    private static final String CONTENT_TYPE = "application/json";

    @Override
    public Credentials getCredentialsOf(ServletRequest request) {
        if (!CONTENT_TYPE.equals(request.getContentType()))
            throw new RuntimeException("invalid request content type");
        return getCredentialsFromBody(request);
    }

    private static Credentials getCredentialsFromBody(ServletRequest request) {
        try (BufferedReader reader = request.getReader()) {
            return getCredentialsOf(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Credentials getCredentialsOf(BufferedReader reader) throws IOException {
        final var objectMapper = new ObjectMapper();
        final var jsonNode = objectMapper.readTree(reader);
        final var username = getOrThrowProperty(jsonNode.get("username"));
        final var password = getOrThrowProperty(jsonNode.get("password"));
        return new Credentials(username, password);
    }

    private static String getOrThrowProperty(JsonNode key) {
        return Optional.ofNullable(key)
                .map(JsonNode::asText)
                .filter(Predicate.not(String::isEmpty))
                .orElseThrow(() -> new RuntimeException("invalid request format"));
    }
}
