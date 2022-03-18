package com.example.campusdirecter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = {"module"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {

    @EqualsAndHashCode.Include
    private final String code;
    private String name;
    private Module module;
    private List<Examination> examinations;

    public Course(String code, String name, Module module, Collection<? extends Examination> examinations) {
        this.code = code;
        this.name = name;
        this.module = module;
        this.examinations = new ArrayList<>(examinations);
    }
}
