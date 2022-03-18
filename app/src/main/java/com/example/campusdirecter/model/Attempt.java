package com.example.campusdirecter.model;

import lombok.Value;

@Value
public class Attempt {

    Examination examination;
    float grade;
    int counter;
}
