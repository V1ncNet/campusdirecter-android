package com.example.campusdirecter.model;

public class Attempt {
    private Examination examination;
    private int grade;
    private int counter;

    public Attempt(Examination examination, int grade, int counter) {
        this.examination = examination;
        this.grade = grade;
        this.counter = counter;
    }

    public Examination getExamination() {
        return examination;
    }

    public int getGrade() {
        return grade;
    }

    public int getCounter() {
        return counter;
    }
}
