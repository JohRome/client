package com.jromeo.http;

import com.google.gson.Gson;
import com.jromeo.dto.AuthDto;
import com.jromeo.dto.TokenDto;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthApi {
    @Getter
    @Setter
    private String jwtToken;

    //FUNKAR
    public boolean login(AuthDto dto) {
        Gson gson = new Gson();
        String json = gson.toJson(dto);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest post = HttpRequest.newBuilder()
                    .uri(new URI("http://school-mangement.eu-north-1.elasticbeanstalk.com:8080/api/v1/auth/authenticate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(post, HttpResponse.BodyHandlers.ofString());

            // Check response status code
            if (response.statusCode() != 200) {
                System.out.println("Failed to login. HTTP error code: " + response.statusCode());
                return false;
            }

            // Check response body for valid token
            TokenDto jwtToken = gson.fromJson(response.body(), TokenDto.class);
            if (jwtToken == null || jwtToken.getAccess_token() == null) {
                System.out.println("Failed to parse token from response.");
                return false;
            }

            this.jwtToken = jwtToken.getAccess_token();
            return true;

        } catch (Exception e) {
            System.out.println("Error when logging in: " + e.getMessage());
            return false;
        }
    }





    // FUNKAR
    public void register(AuthDto dto) {
        Gson gson = new Gson();
        String json = gson.toJson(dto);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest post = HttpRequest.newBuilder()
                    .uri(new URI("http://school-mangement.eu-north-1.elasticbeanstalk.com:8080/api/v1/auth/userRegister"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(post, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            if (response.statusCode() == 200) {

            } else {
                throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Error with token: " + e.getMessage());

        }
    }
}

