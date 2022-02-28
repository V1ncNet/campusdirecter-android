package com.example.campusdirecter.model;

public class Lecture {
    private String id;
    private String summary;
    private Lecturer lecturer;
    private Location location;

    public Lecture(String id, String summary, Lecture, Lecturer lecturer, Location location) {
        this.id = id;
        this.summary = summary;
        this.lecturer = lecturer;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public Location getLocation() {
        return location;
    }
}
