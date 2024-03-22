package com.jromeo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jromeo.dto.StudentDto;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;

public class StudentApi {

    private HttpHelper httpHelper;

    public StudentApi(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public void addStudent(String token, String json) {
        HttpResponse<String> response = httpHelper.sendPostRequest("/student/save", json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }
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

    public void assignCourseToStudent(String token, long studentId, long courseId) {
        HttpResponse<String> response = httpHelper.sendPatchRequest("/student/" + studentId + "/course/" + courseId, "", token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }


}
