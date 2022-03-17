package com.example.campusdirecter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Module {

    private String code;
    private int credits;
    private List<Course> courses;

    public Module(String code, int credits, Collection<? extends Course> courses) {
        this.code = code;
        this.credits = credits;
        this.courses = new ArrayList<>(courses);
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
