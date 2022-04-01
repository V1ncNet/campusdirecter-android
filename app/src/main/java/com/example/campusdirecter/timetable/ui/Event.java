package com.example.campusdirecter.timetable.ui;

import androidx.annotation.Nullable;

import com.example.campusdirecter.model.Lecture;
import com.example.campusdirecter.model.Name;
import com.example.campusdirecter.model.Person;

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

    @Nullable
    public String getLecturer() {
        return lectures.stream()
                .findFirst()
                .map(Lecture::getLecturer)
                .map(Person::getName)
                .map(Name::toString)
                .map(String::trim)
                .orElse(null);
    }

    @Nullable
    public String getLocation() {
        return lectures.stream()
                .findFirst()
                .map(Lecture::getLocation)
                .orElse(null);
    }
}
