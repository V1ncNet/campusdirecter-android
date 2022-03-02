package com.example.campusdirecter.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Interval {
    private LocalDateTime start;
    // duration in minutes
    private long duration;

    public Interval(LocalDateTime start, long duration) {
        this.start = start;
        this.duration = duration;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public long getDuration() {
        return duration;
    }

    public LocalDateTime getEnd() {
        return start.plus(duration, ChronoUnit.MINUTES);
    }
}
