package com.example.campusdirecter.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class IntervalTest {

    private static LocalDateTime now;

    @BeforeClass
    public static void beforeClass() throws Exception {
        now = LocalDateTime.now();
    }

    @Test
    public void determiningEndDate_shouldInterpretMinuteDuration() {
        Interval interval = new Interval(now, "90m");

        LocalDateTime end = interval.getEnd();

        assertEquals(now.plus(90, ChronoUnit.MINUTES), end);
    }
}