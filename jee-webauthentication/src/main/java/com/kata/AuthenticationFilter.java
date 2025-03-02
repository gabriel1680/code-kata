package com.kata;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private final PostAuthenticationFilter postAuthenticationFilter;
    private final GetAuthenticationFilter getAuthenticationFilter;

    public AuthenticationFilter(LdapAuthenticationGateway authenticationGateway) {
        postAuthenticationFilter = new PostAuthenticationFilter(authenticationGateway);
        getAuthenticationFilter = new GetAuthenticationFilter(authenticationGateway);
    }

    @Override
    public void init(FilterConfig config) {
        getAuthenticationFilter.init(config);
        postAuthenticationFilter.init(config);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final var method = ((HttpServletRequest) request).getMethod();
        final var authenticator = getAuthenticator(method);
        authenticator.doFilter(request, response, chain);
    }

    private Filter getAuthenticator(String method) {
        return switch (method) {
            case "POST" -> postAuthenticationFilter;
            case "GET" -> getAuthenticationFilter;
            default -> throw new RuntimeException("invalid request method");
        };
    }


    @Override
    public void destroy() {
        getAuthenticationFilter.destroy();
        postAuthenticationFilter.destroy();
    }

    private record PostAuthenticationFilter(LdapAuthenticationGateway authenticationGateway) implements Filter {

        @Override
        public void init(FilterConfig filterConfig) {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            if (!request.getContentType().equals("application/json"))
                throw new RuntimeException("invalid request content type");
            try (BufferedReader reader = request.getReader()) {
                final var objectMapper = new ObjectMapper();
                final var jsonNode = objectMapper.readTree(reader);
                final var usernameJsonNode = jsonNode.get("username");
                final var passwordJsonNode = jsonNode.get("password");
                if (usernameJsonNode == null || usernameJsonNode.asText("").isEmpty() || passwordJsonNode == null || passwordJsonNode.asText("").isEmpty())
                    throw new RuntimeException("invalid request format");
                final var userName = usernameJsonNode.asText();
                final var password = passwordJsonNode.asText();
                if (!authenticationGateway.credentialsAreValid(userName, password))
                    throw new RuntimeException("invalid username or password");
                chain.doFilter(request, response);
            }
        }

        @Override
        public void destroy() {
        }
    }

    private record GetAuthenticationFilter(LdapAuthenticationGateway authenticationGateway) implements Filter {

        @Override
        public void init(FilterConfig filterConfig) {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            final var username = request.getParameter("username");
            if (username == null || username.isEmpty())
                throw new RuntimeException("invalid request missing username");
            final var password = request.getParameter("password");
            if (password == null || password.isEmpty())
                throw new RuntimeException("invalid request missing password");
            if (!authenticationGateway.credentialsAreValid(username, password))
                throw new RuntimeException("invalid username or password");
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
        }
    }
}
