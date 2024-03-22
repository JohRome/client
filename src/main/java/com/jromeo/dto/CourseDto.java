package com.jromeo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private long courseId;
    private String title;
    private String abbreviation;
    private int modules;
    private double fee;
}
