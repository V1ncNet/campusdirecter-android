package com.example.campusdirecter.model;

public class Course {
    private String code;
    private String name;
    private Module module;

    public Course(String code, String name, Module module) {
        this.code = code;
        this.name = name;
        this.module = module;
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
}
