package com.example.campusdirecter.timetable.ui;

import com.example.campusdirecter.model.Lecture;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {

    @EqualsAndHashCode.Include
    String name;
    List<Lecture> lectures;
}
