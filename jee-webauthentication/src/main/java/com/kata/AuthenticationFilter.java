package com.kata;

import com.fasterxml.jackson.databind.JsonNode;
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
import java.util.Optional;

public class AuthenticationFilter implements Filter {

    private final LdapAuthenticationGateway authenticationGateway;
    private final PostCredentialParser postCredentialParser;
    private final GetCredentialParser getCredentialParser;

    public AuthenticationFilter(LdapAuthenticationGateway authenticationGateway) {
        this.authenticationGateway = authenticationGateway;
        this.postCredentialParser = new PostCredentialParser();
        this.getCredentialParser = new GetCredentialParser();
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final var method = ((HttpServletRequest) request).getMethod();
        final var requestParser = getRequestParser(method);
        final var credentials = requestParser.getCredentialsOf(request);
        if (!authenticationGateway.credentialsAreValid(credentials.username, credentials.password))
            throw new RuntimeException("invalid username or password");
        chain.doFilter(request, response);
    }

    private RequestCredentialParser getRequestParser(String method) {
        return switch (method) {
            case "POST" -> postCredentialParser;
            case "GET" -> getCredentialParser;
            default -> throw new RuntimeException("invalid request method");
        };
    }


    @Override
    public void destroy() {
    }

    private interface RequestCredentialParser {
        Credentials getCredentialsOf(ServletRequest request);
    }

    private record Credentials(String username, String password) {
    }

    private static class PostCredentialParser implements RequestCredentialParser {
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

    private static class GetCredentialParser implements RequestCredentialParser {
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
}
