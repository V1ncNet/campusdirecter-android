package com.example.campusdirecter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Module {

    @EqualsAndHashCode.Include
    private final String code;
    private int credits;
    private List<Course> courses;

    public Module(String code, int credits, Collection<? extends Course> courses) {
        this.code = code;
        this.credits = credits;
        this.courses = new ArrayList<>(courses);
    }

    public List<Examination> getExaminations() {
        return courses.stream()
                .map(Course::getExaminations)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
