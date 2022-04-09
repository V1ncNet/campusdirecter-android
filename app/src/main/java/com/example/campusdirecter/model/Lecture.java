package com.example.campusdirecter.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public LocalTime getStartTime() {
        return interval.getStart().toLocalTime();
    }

    public LocalTime getEndTime() {
        return interval.getEnd().toLocalTime();
    }
}
