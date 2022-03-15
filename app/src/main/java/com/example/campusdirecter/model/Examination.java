package com.example.campusdirecter.model;

public class Examination {
    private String code;
    private String name;
    private Course course;
    private Interval interval;
    private String location;
    private Lecturer supervision;

    public Examination(String code, String name, Course course, Interval interval, String location, Lecturer supervision) {
        this.code = code;
        this.name = name;
        this.course = course;
        this.interval = interval;
        this.location = location;
        this.supervision = supervision;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public Interval getInterval() {
        return interval;
    }

    public String getLocation() {
        return location;
    }

    public Lecturer getSupervision() {
        return supervision;
    }
}
