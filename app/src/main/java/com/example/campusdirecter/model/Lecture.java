package com.example.campusdirecter.model;

import lombok.Value;

@Value
public class Lecture {

    String summary;
    Lecturer lecturer;
    String location;
    Interval interval;
}
