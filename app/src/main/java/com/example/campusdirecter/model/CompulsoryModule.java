package com.example.campusdirecter.model;

import java.util.Collection;

public class CompulsoryModule extends Module{

    public CompulsoryModule(String code, Collection<? extends Course> courses, int credits) {
        super(code, courses, credits);
    }
}
