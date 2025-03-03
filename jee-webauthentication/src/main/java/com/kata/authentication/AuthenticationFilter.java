package com.kata.authentication;

import com.kata.LdapAuthenticationGateway;
import com.kata.SingleSignOnRegistry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class AuthenticationFilter implements Filter {

    private static final String COOKIE_TOKEN = "x-sso-token";

    private final LdapAuthenticationGateway authenticationGateway;
    private final SingleSignOnRegistry ssoRegistry;
    private final PostCredentialParser postCredentialParser;
    private final GetCredentialParser getCredentialParser;

    public AuthenticationFilter(LdapAuthenticationGateway authenticationGateway, SingleSignOnRegistry ssoRegistry) {
        this.authenticationGateway = authenticationGateway;
        this.ssoRegistry = ssoRegistry;
        this.postCredentialParser = new PostCredentialParser();
        this.getCredentialParser = new GetCredentialParser();
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        validateToken((HttpServletRequest) request);
        final var credentials = validateCredentials(request);
        registerNewSession((HttpServletResponse) response, credentials);
        chain.doFilter(request, response);
    }

    private void validateToken(HttpServletRequest request) {
        getAuthTokenFrom(request).ifPresent(token -> {
            if (!ssoRegistry.tokenIsValid(token))
                throw new RuntimeException("invalid token");
        });
    }

    private Credentials validateCredentials(ServletRequest request) {
        final var credentials = getCredentialsFrom(request);
        if (!authenticationGateway.credentialsAreValid(credentials.username(), credentials.password()))
            throw new RuntimeException("invalid username or password");
        return credentials;
    }

    private Credentials getCredentialsFrom(ServletRequest request) {
        final var method = ((HttpServletRequest) request).getMethod();
        final var requestParser = getRequestParser(method);
        return requestParser.getCredentialsOf(request);
    }

    private static Optional<String> getAuthTokenFrom(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> COOKIE_TOKEN.equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue);
    }

    private RequestCredentialParser getRequestParser(String method) {
        return switch (method) {
            case "POST" -> postCredentialParser;
            case "GET" -> getCredentialParser;
            default -> throw new RuntimeException("invalid request method");
        };
    }

    private void registerNewSession(HttpServletResponse response, Credentials credentials) {
        final var token = ssoRegistry.registerNewSession(credentials.username());
        response.addCookie(new Cookie(COOKIE_TOKEN, token));
    }

    @Override
    public void destroy() {
    }
}
