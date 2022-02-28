package com.example.campusdirecter.model;

public class FieldOfDegree {
    private String name;
    private String degree;
    private Module[] modules;
    private Student[] students;

    public FieldOfDegree(String name, String degree, Module[] modules, Student[] students) {
        this.name = name;
        this.degree = degree;
        this.modules = modules;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }

    public Module[] getModules() {
        return modules;
    }

    public Student[] getStudents() {
        return students;
    }
}
