package com.example.campusdirecter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Module {

    private String code;
    private List<Course> courses;
    private int credits;

    public Module(String code, Collection<? extends Course> courses, int credits) {
        this.code = code;
        this.courses = new ArrayList<>(courses);
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int getCredits() {
        return credits;
    }

    public List<Examination> getExaminations() {
        return courses.stream()
                .map(Course::getExaminations)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
