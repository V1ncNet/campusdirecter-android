package com.example.campusdirecter.model;

public class ElectiveModule extends Module{
    // for modules with multiple courses
    public ElectiveModule(String code, Course[] courses, int credits) {
        super(code, courses, credits);
    }

    // for modules with one course
    public ElectiveModule(String code, Course course, int credits) {
        super(code, course, credits);
    }
}
