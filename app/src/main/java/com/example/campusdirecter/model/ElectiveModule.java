package com.example.campusdirecter.model;

import java.util.Collection;

public class ElectiveModule extends Module {

    public ElectiveModule(String code, int credits, Collection<? extends Course> courses) {
        super(code, credits, courses);
    }
}
