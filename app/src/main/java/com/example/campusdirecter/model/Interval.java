package com.example.campusdirecter.model;

import java.util.Date;

public class Interval {
    private Date start;
    // duration in minutes
    private int duration;

    public Interval(Date start, int duration) {
        this.start = start;
        this.duration = duration;
    }

    public Date getStart() {
        return start;
    }


    public int getDuration() {
        return duration;
    }
}
