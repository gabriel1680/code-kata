package com.kata.authentication;

import javax.servlet.ServletRequest;

class GetCredentialParser implements RequestCredentialParser {
    @Override
    public Credentials getCredentialsOf(ServletRequest request) {
        final var username = getParameterOrThrow(request, "username");
        final var password = getParameterOrThrow(request, "password");
        return new Credentials(username, password);
    }

    private static String getParameterOrThrow(ServletRequest request, String key) {
        final var value = request.getParameter(key);
        if (value == null || value.isEmpty())
            throw new RuntimeException("invalid request missing " + key);
        return value;
    }
}
