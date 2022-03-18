package com.example.campusdirecter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(exclude = {"course"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Examination {

    @EqualsAndHashCode.Include
    private final String code;
    private String name;
    private Course course;
    private Interval interval;
    private String location;
    private Lecturer supervision;
}
