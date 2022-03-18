package com.example.campusdirecter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {

    @EqualsAndHashCode.Include
    private final String id;
    private Name name;
}
