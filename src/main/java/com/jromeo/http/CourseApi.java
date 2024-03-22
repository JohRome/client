package com.jromeo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jromeo.dto.CourseDto;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseApi {
    private HttpHelper httpHelper;

    public CourseApi(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public CourseDto getCourseById(String jwt, long courseId) {
        HttpResponse<String> response = httpHelper.sendGetRequest("/course/getCourse/" + courseId, jwt);
        Gson gson = new Gson();
        return gson.fromJson(response.body(), CourseDto.class);
    }


    public List<CourseDto> getAllCourses(String jwt) {
        HttpResponse<String> response = httpHelper.sendGetRequest("/course/getCourses", jwt);
        Gson gson = new Gson();
        Type courseType = new TypeToken<List<CourseDto>>(){}.getType();
        return gson.fromJson(response.body(), courseType);
    }
    // Add Course
    public void addCourse(String token, String json) {
        HttpResponse<String> response = httpHelper.sendPostRequest("/courses/save/", json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }

    // Update Course
    public void updateCourse(String token, long id,String json) {
        HttpResponse<String> response = httpHelper.sendPutRequest("/course/update/" + id, json, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }

    // Delete Course
    public void deleteCourse(String token, long id) {
        HttpResponse<String> response = httpHelper.sendDeleteRequest("/course/delete/" + id, token);
        boolean isSuccess = response.statusCode() > 199 || response.statusCode() < 300;
        if (!isSuccess) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        System.out.println(response.body());
    }
}
