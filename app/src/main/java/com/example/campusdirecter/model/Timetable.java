package com.example.campusdirecter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Timetable {

    @EqualsAndHashCode.Include
    private final String id;
    private String summary;
    private Student owner;
    private Lecture[] lectures;
}
