package com.example.campusdirecter.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.ToString;
import lombok.Value;

@Value
@ToString(doNotUseGetters = true) // FIXME: Fix parse function
public class Interval {

    LocalDateTime start;
    String duration;

    public Duration getDuration() {
        return parse(duration);
    }

    public LocalDateTime getEnd() {
        return start.plus(getDuration());
    }

    private Duration parse(String duration) {
        // TODO: Parse SI unit
        String decimalValue = duration.replaceAll("/D", "");
        return Duration.of(Long.parseLong(decimalValue), ChronoUnit.MINUTES);
    }
}
