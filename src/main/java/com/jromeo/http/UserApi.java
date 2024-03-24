package com.jromeo.http;

import java.net.http.HttpResponse;

public class UserApi {

    private HttpHelper httpHelper;

    public UserApi() {
        this.httpHelper = new HttpHelper();
    }

    public void updateUser(String token, String userEmail, String json) {
        HttpResponse<String> putResponse = httpHelper.sendPutRequest("/users/update/" + userEmail, json, token);
        if (putResponse.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + putResponse.statusCode());
        }
    }

    public void changePassword(String token, String json) {
        HttpResponse<String> putResponse = httpHelper.sendPutRequest("/users", json, token);
        if (putResponse.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + putResponse.statusCode());
        }
    }
}
