package com.example.campusdirecter.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Module {
    private String code;
    private Course[] courses;
    private Course course;
    private int credits;

    // for modules with multiple courses
    public Module(String code, Course[] courses, int credits) {
        this.code = code;
        this.courses = courses;
        this.credits = credits;
    }

    // for modules with one course
    public Module(String code, Course course, int credits) {
        this.code = code;
        this.course = course;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public Course[] getCourses() {
        return courses;
    }

    public Course getCourse() {
        return course;
    }

    public int getCredits() {
        return credits;
    }

    public List<Examination> getExaminations() {
        return Arrays.stream(courses)
                .map(Course::getExaminations)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
