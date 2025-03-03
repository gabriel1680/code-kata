package com.kata.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

class PostCredentialParser implements RequestCredentialParser {
    @Override
    public Credentials getCredentialsOf(ServletRequest request) {
        if (!request.getContentType().equals("application/json"))
            throw new RuntimeException("invalid request content type");
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
                .filter(i -> !i.isEmpty())
                .orElseThrow(() -> new RuntimeException("invalid request format"));
    }
}
