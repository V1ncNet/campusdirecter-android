package com.example.campusdirecter.model;

import java.util.Collection;

public class ElectiveModule extends Module{

    public ElectiveModule(String code, Collection<? extends Course> courses, int credits) {
        super(code, courses, credits);
    }
}
