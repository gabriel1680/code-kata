package com.kata.authentication;

import javax.servlet.ServletRequest;

class GetCredentialParser implements RequestCredentialParser {
    @Override
    public Credentials getCredentialsOf(ServletRequest request) {
        final var username = request.getParameter("username");
        if (username == null || username.isEmpty())
            throw new RuntimeException("invalid request missing username");
        final var password = request.getParameter("password");
        if (password == null || password.isEmpty())
            throw new RuntimeException("invalid request missing password");
        return new Credentials(username, password);
    }
}
