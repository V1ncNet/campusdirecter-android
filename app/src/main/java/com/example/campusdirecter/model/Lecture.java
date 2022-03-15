package com.example.campusdirecter.model;

public class Lecture {
    private String summary;
    private Lecturer lecturer;
    private String location;
    private Interval interval;

    public Lecture(String summary, Lecturer lecturer, String location, Interval interval) {
        this.summary = summary;
        this.lecturer = lecturer;
        this.location = location;
        this.interval = interval;
    }
    public String getSummary() {
        return summary;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public String getLocation() {
        return location;
    }

    public Interval getInterval() {
        return interval;
    }
}
