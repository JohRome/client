package com.jromeo.http;

import com.jromeo.dto.AuthDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthApiTest {
    private AuthApi authApi;
    private HttpClient httpClient;
    private HttpResponse<String> httpResponse;

    @BeforeEach
    void setUp() {
        authApi = new AuthApi();
        httpClient = Mockito.mock(HttpClient.class);
        httpResponse = Mockito.mock(HttpResponse.class);
    }

    @Test
    void shouldReturnFalseWhenLoginWithInvalidEmail() {
        try {
            when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(403);

            AuthDto authDto = new AuthDto();
            authDto.setEmail("invalid_email@mail.com");
            authDto.setPassword("valid_password");

            assertFalse(authApi.login(authDto));
            assertNull(authApi.getJwtToken());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void shouldReturnFalseWhenLoginWithInvalidPassword() {
        try {
            when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(403);

            AuthDto authDto = new AuthDto();
            authDto.setEmail("valid_email@mail.com");
            authDto.setPassword("invalid_password");

            assertFalse(authApi.login(authDto));
            assertNull(authApi.getJwtToken());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testRegister() throws IOException, InterruptedException {
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        AuthDto authDto = new AuthDto();
        authDto.setEmail("valid_email@mail.com");
        authDto.setPassword("valid_password");

        assertDoesNotThrow(() -> authApi.register(authDto));
    }
}
