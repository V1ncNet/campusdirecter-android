package com.example.campusdirecter.model;

public class Attempt {
    private Examination examination;
    private float grade;
    private int counter;

    public Attempt(Examination examination, float grade, int counter) {
        this.examination = examination;
        this.grade = grade;
        this.counter = counter;
    }

    public Examination getExamination() {
        return examination;
    }

    public float getGrade() {
        return grade;
    }

    public int getCounter() {
        return counter;
    }
}
