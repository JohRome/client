package com.jromeo.http;

import java.net.http.HttpResponse;

public class AdminApi {
    private HttpHelper httpHelper;

    // Delete User
    public void deleteUserAsAdmin(String token, String userEmail) {
        HttpResponse<String> response = httpHelper.sendDeleteRequest("/users/delete/" + userEmail, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Convert User to admin
    public void promoteToAdmin(String token, String json) {
        HttpResponse<String> response = httpHelper.sendPostRequest("/users/promoteToAdmin", json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Update Student
    public void updateStudent(String token, long id, String json) {
        HttpResponse<String> response = httpHelper.sendPutRequest("/student/update/" + id, json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Delete Student
    public void deleteStudent(String token, long id) {
        HttpResponse<String> response = httpHelper.sendDeleteRequest("/student/delete/" + id, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Add Course
    public void addCourse(String token, String json) {
        HttpResponse<String> response = httpHelper.sendPostRequest("/courses/save", json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Update Course
    public void updateCourse(String token, long id, String json) {
        HttpResponse<String> response = httpHelper.sendPutRequest("/course/update/" + id, json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Delete Course
    public void deleteCourse(String token, long id) {
        HttpResponse<String> response = httpHelper.sendDeleteRequest("/course/delete/" + id, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
    }
}
