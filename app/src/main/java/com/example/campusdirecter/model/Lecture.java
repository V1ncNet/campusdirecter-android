package com.example.campusdirecter.model;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class Lecture {

    String summary;
    Lecturer lecturer;
    String location;
    Interval interval;

    public LocalDateTime getStart() {
        return interval.getStart();
    }
}
