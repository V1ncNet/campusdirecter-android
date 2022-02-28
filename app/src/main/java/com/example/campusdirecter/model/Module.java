package com.example.campusdirecter.model;

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

    public Course getCourseByIndex(int i) {
        return courses[i];
    }

    public Course getCourse() {
        return course;
    }

    public int getCredits() {
        return credits;
    }
}
