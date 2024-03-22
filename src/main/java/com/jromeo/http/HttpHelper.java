package com.jromeo.http;

import lombok.Getter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpHelper {

    private String baseUrl = "http://school-mangement.eu-north-1.elasticbeanstalk.com:8080";
    @Getter
    private HttpClient httpClient;

    public HttpHelper() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public HttpResponse<String> sendPostRequest(String endpoint, String body, String token) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to send POST request");
        }
    }

    public HttpResponse<String> sendGetRequest(String endpoint, String token) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .build();
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to send GET request");
        }
    }

    public HttpResponse<String> sendPutRequest(String endpoint, String body, String token) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to send PUT request");
        }
    }

    public HttpResponse<String> sendPatchRequest(String endpoint, String body, String token) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(body))
                    .build();
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to send PATCH request");
        }
    }

    public HttpResponse<String> sendDeleteRequest(String endpoint, String token)  {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .DELETE()
                    .build();
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to send DELETE request");
        }
    }
}
