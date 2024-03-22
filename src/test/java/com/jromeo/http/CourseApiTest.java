package com.jromeo.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CourseApiTest {
    private CourseApi courseApi;
    private HttpResponse<String> httpResponse;
    private HttpHelper httpHelper;

    @BeforeEach
    void setUp() {
        httpResponse = Mockito.mock(HttpResponse.class);
        httpHelper = Mockito.mock(HttpHelper.class);
        courseApi = new CourseApi(httpHelper);
    }

    @Test
    void testGetCourseById() {
        when(httpHelper.sendGetRequest(any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("{\"courseId\":1,\"title\":\"Math\",\"abbreviation\":\"MTH\",\"modules\":5,\"fee\":500.0}");

        assertDoesNotThrow(() -> courseApi.getCourseById("valid_token", 1));
    }

    @Test
    void testGetAllCourses() {
        when(httpHelper.sendGetRequest(any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("[{\"courseId\":1,\"title\":\"Math\",\"abbreviation\":\"MTH\",\"modules\":5,\"fee\":500.0}]");

        assertDoesNotThrow(() -> courseApi.getAllCourses("valid_token"));
    }

    @Test
    void testAddCourse() {
        when(httpHelper.sendPostRequest(any(String.class), any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        assertDoesNotThrow(() -> courseApi.addCourse("valid_token", "{\"title\":\"Math\",\"abbreviation\":\"MTH\",\"modules\":5,\"fee\":500.0}"));
    }

    @Test
    void testUpdateCourse() {
        when(httpHelper.sendPutRequest(any(String.class), any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        assertDoesNotThrow(() -> courseApi.updateCourse("valid_token", 1, "{\"title\":\"Math\",\"abbreviation\":\"MTH\",\"modules\":5,\"fee\":500.0}"));
    }

    @Test
    void testDeleteCourse() {
        when(httpHelper.sendDeleteRequest(any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        assertDoesNotThrow(() -> courseApi.deleteCourse("valid_token", 1));
    }
}