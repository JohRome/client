package com.jromeo.http;

import java.net.http.HttpResponse;

public class UserApi {

    private HttpHelper httpHelper;

    public UserApi() {
        this.httpHelper = new HttpHelper();
    }




    // Promote to admin

    // Update user - UserDto needed for json
    public void updateUser(String token, String userEmail, String json) {
        HttpResponse<String> putResponse = httpHelper.sendPutRequest("/users/update/" + userEmail, json, token);
        if (putResponse.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + putResponse.statusCode());
        }
    }

    // Change user password - PasswordDto needed for json
    public void changePassword(String token, String json) {
        HttpResponse<String> putResponse = httpHelper.sendPutRequest("/users", json, token);
        if (putResponse.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + putResponse.statusCode());
        }
    }

    public void addStudent(String token, String json) {
        HttpResponse<String> postResponse = httpHelper.sendPostRequest("/student/save", json, token);
        if (postResponse.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + postResponse.statusCode());
        }
    }
    // /student/{stuId}/course/{courseId}

    public void assignCourseToStudent(String token, int studentId, int courseId) {
        HttpResponse<String> postResponse = httpHelper.sendPostRequest("/student/" + studentId + "/course/" + courseId, "", token);
        if (postResponse.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + postResponse.statusCode());
        }
    }
}
