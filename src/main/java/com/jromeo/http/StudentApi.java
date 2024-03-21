package com.jromeo.http;

import java.net.http.HttpResponse;

public class StudentApi {

    private HttpHelper httpHelper;

    public StudentApi() {
        this.httpHelper = new HttpHelper();
    }

    // Add student

    // Get one student
    public void getStudent(String token, long id) {
        HttpResponse<String> getResponse = httpHelper.sendGetRequest("/student/" + id, token);
        if (getResponse.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + getResponse.statusCode());
        }
    }

    // Get all Students
    public void getAllStudents(String token) {
        HttpResponse<String> getResponse = httpHelper.sendGetRequest("/student/all", token);
        if (getResponse.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + getResponse.statusCode());
        }
    }


    // Delete student - long id needed


}
