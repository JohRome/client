package com.jromeo.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StudentApiTest {
    private StudentApi studentApi;
    private HttpResponse<String> httpResponse;
    private HttpHelper httpHelper;

    @BeforeEach
    void setUp() {
        httpResponse = Mockito.mock(HttpResponse.class);
        httpHelper = Mockito.mock(HttpHelper.class);
        studentApi = new StudentApi(httpHelper);
    }

    @Test
    void testAddStudent() {
        when(httpHelper.sendPostRequest(any(String.class), any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        assertDoesNotThrow(() -> studentApi.addStudent("valid_token", "{\"name\":\"John\",\"age\":20}"));
    }

    @Test
    void testGetStudent() {
        when(httpHelper.sendGetRequest(any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("{\"id\":1,\"name\":\"John\",\"age\":20}");

        assertDoesNotThrow(() -> studentApi.getStudent("valid_token", 1));
    }

    @Test
    void testGetAllStudents() {
        when(httpHelper.sendGetRequest(any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("[{\"id\":1,\"name\":\"John\",\"age\":20}]");

        assertDoesNotThrow(() -> studentApi.getAllStudents("valid_token"));
    }

    @Test
    void testUpdateStudent() {
        when(httpHelper.sendPutRequest(any(String.class), any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        assertDoesNotThrow(() -> studentApi.updateStudent("valid_token", 1, "{\"name\":\"John\",\"age\":21}"));
    }

    @Test
    void testDeleteStudent() {
        when(httpHelper.sendDeleteRequest(any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        assertDoesNotThrow(() -> studentApi.deleteStudent("valid_token", 1));
    }

    @Test
    void testAssignCourseToStudent() {
        when(httpHelper.sendPatchRequest(any(String.class), any(String.class), any(String.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        assertDoesNotThrow(() -> studentApi.assignCourseToStudent("valid_token", 1, 1));
    }
}