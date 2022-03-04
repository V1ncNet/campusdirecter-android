package com.example.campusdirecter.model;

public class CompulsoryModule extends Module{
    // for modules with multiple courses
    public CompulsoryModule(String code, Course[] courses, int credits) {
        super(code, courses, credits);
    }

    // for modules with one course
    public CompulsoryModule(String code, Course course, int credits) {
        super(code, course, credits);
    }
}
