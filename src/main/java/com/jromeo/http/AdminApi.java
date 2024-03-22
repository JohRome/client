package com.jromeo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jromeo.dto.StudentDto;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;

public class AdminApi {
    private HttpHelper httpHelper;

    public AdminApi(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    // Get one student - FUNGERAR
    public StudentDto getStudent(String token, long id) {
        HttpResponse<String> response = httpHelper.sendGetRequest("/student/getStudent/" + id, token);
        Gson gson = new Gson();
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        return gson.fromJson(response.body(), StudentDto.class);
    }

    // Get all Students - FUNGERAR
    public List<StudentDto> getAllStudents(String token) {
        HttpResponse<String> response = httpHelper.sendGetRequest("/student/getStudents", token);

        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<StudentDto>>() {
        }.getType();
        return gson.fromJson(response.body(), listType);
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

    // Update Student
    public void updateStudent(String token, long id, String json) {
        HttpResponse<String> response = httpHelper.sendPutRequest("/student/update/" + id, json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }

    // Delete Student
    public void deleteStudent(String token, long id) {
        HttpResponse<String> response = httpHelper.sendDeleteRequest("/student/delete/" + id, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }
}
