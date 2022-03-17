package com.example.campusdirecter.model;

import java.util.Collection;

public class CompulsoryModule extends Module {

    public CompulsoryModule(String code, int credits, Collection<? extends Course> courses) {
        super(code, credits, courses);
    }
}
