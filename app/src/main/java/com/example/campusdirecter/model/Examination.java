package com.example.campusdirecter.model;

public class Examination {
    private String code;
    private String name;
    private String course;
    private Interval interval;
    private Location location;
    private Lecturer supervision;

    public Examination(String code, String name, String course, Interval interval, Location location, Lecturer supervision) {
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

    public String getCourse() {
        return course;
    }

    public Interval getInterval() {
        return interval;
    }

    public Location getLocation() {
        return location;
    }

    public Lecturer getSupervision() {
        return supervision;
    }
}
