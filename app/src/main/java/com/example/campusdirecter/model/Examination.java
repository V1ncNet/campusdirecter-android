package com.example.campusdirecter.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Examination that = (Examination) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
