package com.example.campusdirecter.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Interval {
    private LocalDateTime start;
    // duration in minutes
    private String duration;

    public Interval(LocalDateTime start, String duration) {
        this.start = start;
        this.duration = duration;
    }

    private Duration parse(String duration) {
        // TODO: Parse SI unit
        String decimalValue = duration.replaceAll("/D*", "");
        return Duration.of(Long.parseLong(decimalValue), ChronoUnit.MINUTES);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getDuration() {
        return parse(duration);
    }

    public LocalDateTime getEnd() {
        return start.plus(parse(duration));
    }
}
