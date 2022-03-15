package com.example.campusdirecter.model;

public class Timetable {
    private String id;
    private String summary;
    private Student owner;
    private Lecture[] lectures;

    public Timetable(String id, String summary, Student student, Lecture[] lectures) {
        this.id = id;
        this.summary = summary;
        this.owner = student;
        this.lectures = lectures;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public Student getOwner() {
        return owner;
    }

    public Lecture[] getEvents() {
        return lectures;
    }
}
