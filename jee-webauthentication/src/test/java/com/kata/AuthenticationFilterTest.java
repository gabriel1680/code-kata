package com.kata;

import com.kata.authentication.AuthenticationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationFilterTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    FilterChain chain;

    LdapAuthenticationGateway authenticationGateway;
    SingleSignOnRegistry ssoRegistry;
    AuthenticationFilter sut;

    static class FakeAuthenticationGateway implements LdapAuthenticationGateway {
        @Override
        public boolean credentialsAreValid(String userName, String password) {
            return userName.equals("John Doe") && password.equals("john_doe123");
        }
    }

    @BeforeEach
    void setUp() {
        authenticationGateway = new FakeAuthenticationGateway();
        ssoRegistry = new FakeSSORegistry();
        sut = new AuthenticationFilter(authenticationGateway, ssoRegistry);
        when(request.getCookies()).thenReturn(new Cookie[]{});
    }

    @Test
    void invalidMethodRequest() {
        when(request.getMethod()).thenReturn("PUT");
        assertThatThrownBy(() -> sut.doFilter(request, response, chain)).hasMessage("invalid request method");
    }

    @Test
    void invalidFormatRequest_onGET1() {
        when(request.getMethod()).thenReturn("GET");
        assertThatThrownBy(() -> sut.doFilter(request, response, chain)).hasMessage("invalid request missing username");
    }

    @Test
    void invalidFormatRequest_onGET2() {
        when(request.getMethod()).thenReturn("GET");
        when(request.getParameter("username")).thenReturn("John Doe");
        assertThatThrownBy(() -> sut.doFilter(request, response, chain)).hasMessage("invalid request missing password");
    }

    @Test
    void invalidGatewayAuthentication_onGET() {
        when(request.getMethod()).thenReturn("GET");
        when(request.getParameter("username")).thenReturn("invalid");
        when(request.getParameter("password")).thenReturn("john_doe123");
        assertThatThrownBy(() -> sut.doFilter(request, response, chain)).hasMessage("invalid username or password");
    }

    @Test
    void validGatewayAuthentication_onGET() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getParameter("username")).thenReturn("John Doe");
        when(request.getParameter("password")).thenReturn("john_doe123");
        sut.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(any(), any());
    }

    @Test
    void invalidJsonFormatRequest_onPOST() throws IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getContentType()).thenReturn("application/json");
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("abc")));
        assertThatThrownBy(() -> sut.doFilter(request, response, chain));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "{}",
            "{\"username\":\"\"}",
            "{\"username\":\"John Doe\"}",
            "{\"username\":\"John Doe\",\"password\":\"\"}"
    })
    void emptyFieldsRequest_onPOST(String jsonBody) throws IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getContentType()).thenReturn("application/json");
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonBody)));
        assertThatThrownBy(() -> sut.doFilter(request, response, chain)).hasMessage("invalid request format");
    }

    @Test
    void validRequestRequest_failOnAuthenticate_onPOST() throws IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getContentType()).thenReturn("application/json");
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"username\":\"John Doe\",\"password\":\"123\"}")));
        assertThatThrownBy(() -> sut.doFilter(request, response, chain)).hasMessage("invalid username or password");
    }

    @Test
    void validRequestRequest_okOnAuthenticate_onPOST() throws IOException, ServletException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getContentType()).thenReturn("application/json");
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"username\":\"John Doe\",\"password\":\"john_doe123\"}")));
        sut.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(any(), any());
    }

    static class FakeSSORegistry implements SingleSignOnRegistry {


        @Override
        public boolean tokenIsValid(String token) {
            return "1".equals(token);
        }

        @Override
        public String registerNewSession(String userName) {
            return userName.equals("John Doe") ? "1" : "2";
        }

        @Override
        public void endSession(String token) {
        }
    }

    @Captor
    ArgumentCaptor<Cookie> cookieCaptor;

    @Test
    void afterSuccessfullyLogin_shouldRegisterTheSessionOnACookie() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getParameter("username")).thenReturn("John Doe");
        when(request.getParameter("password")).thenReturn("john_doe123");
        sut.doFilter(request, response, chain);
        verify(response, times(1)).addCookie(cookieCaptor.capture());
        final var cookie = cookieCaptor.getValue();
        assertThat(cookie.getName()).isEqualTo("x-sso-token");
        assertThat(cookie.getValue()).isEqualTo("1");
    }

    @Test
    void cookieWithInvalidSSOSessionId() {
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("x-sso-token", "5")});
        assertThatThrownBy(() -> sut.doFilter(request, response, chain)).hasMessage("invalid token");
    }

    @Test
    void cookieWithValidSSOSessionId() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getParameter("username")).thenReturn("John Doe");
        when(request.getParameter("password")).thenReturn("john_doe123");
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("x-sso-token", "1")});
        sut.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(any(), any());
    }
}