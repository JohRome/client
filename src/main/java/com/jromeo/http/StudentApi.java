package com.jromeo.http;

import java.net.http.HttpResponse;

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

    public void assignCourseToStudent(String token, long studentId, long courseId) {
        HttpResponse<String> response = httpHelper.sendPatchRequest("/student/" + studentId + "/course/" + courseId, "", token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }


}
