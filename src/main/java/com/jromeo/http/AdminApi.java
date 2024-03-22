package com.jromeo.http;

import java.net.http.HttpResponse;

public class AdminApi {
    private HttpHelper httpHelper;

    public AdminApi(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    // Delete User
    public void deleteUserAsAdmin(String token, String userEmail) {
        HttpResponse<String> response = httpHelper.sendDeleteRequest("/users/delete/" + userEmail, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }

    // Convert User to admin
    public void promoteToAdmin(String token, String json) {
        HttpResponse<String> response = httpHelper.sendPostRequest("/users/promoteToAdmin", json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }

}
