package com.example.campusdirecter.model;

public class Student extends Person{
    private String seminarGroup;
    private Progression progression;

    public Student(String id, Name name, String seminarGroup, Progression progression) {
        super(id, name);
        this.seminarGroup = seminarGroup;
        this.progression = progression;
    }
    public String getSeminarGroup() {
        return seminarGroup;
    }

    public Progression getProgression() {
        return progression;
    }
}
