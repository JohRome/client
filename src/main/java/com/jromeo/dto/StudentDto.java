package com.jromeo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private long studentId;
    private String name;
    private int age;
    private String dept;
    private Set<CourseDto> courses;
}
