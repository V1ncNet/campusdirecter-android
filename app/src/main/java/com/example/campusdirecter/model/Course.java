package com.example.campusdirecter.model;

import java.util.List;

public class Course {
    private String code;
    private String name;
    private Module module;
    private List<Examination> examinations;

    public Course(String code, String name, Module module, List<Examination> examinations) {
        this.code = code;
        this.name = name;
        this.module = module;
        this.examinations = examinations;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Module getModule() {
        return module;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }
}
