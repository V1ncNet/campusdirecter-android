package com.example.campusdirecter.timetable.ui;

import java.time.LocalDate;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Day {

    @EqualsAndHashCode.Include
    LocalDate date;
    List<Event> events;
}
