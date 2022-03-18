package com.example.campusdirecter.model;

import lombok.Value;

@Value
public class FieldOfDegree {

    String name;
    String degree;
    Module[] modules;
    Student[] students;
}
