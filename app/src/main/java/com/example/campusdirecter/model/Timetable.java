package com.example.campusdirecter.model;

import android.app.usage.UsageEvents;

public class Timetable {
    private String id;
    private String summary;
    private Student owner;
    private Event[] events;

    public Timetable(String id, String summary, Student student, Event[] events) {
        this.id = id;
        this.summary = summary;
        this.owner = student;
        this.events = events;
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

    public Event[] getEvents() {
        return events;
    }
}
