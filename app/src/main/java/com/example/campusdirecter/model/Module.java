package com.example.campusdirecter.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
        List<Examination> exams = new ArrayList<>();
        for (Course c : courses) {
            exams.addAll(c.getExaminations());
        }
        return exams;
    }
}
