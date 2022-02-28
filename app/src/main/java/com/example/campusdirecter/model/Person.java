package com.example.campusdirecter.model;

public class Person {
    private String id;
    private Name name;

    public Person(String id, Name name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Name getName() {
        return name;
    }
}
