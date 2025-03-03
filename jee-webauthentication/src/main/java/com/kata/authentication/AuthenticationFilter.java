package com.kata.authentication;

import com.kata.LdapAuthenticationGateway;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        if (!authenticationGateway.credentialsAreValid(credentials.username(), credentials.password()))
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
}
