package com.example.campusdirecter.model;

public class Try {
    private Examination examination;
    private int grade;
    private int attempt;

    public Try(Examination examination, int grade, int attempt) {
        this.examination = examination;
        this.grade = grade;
        this.attempt = attempt;
    }

    public Examination getExamination() {
        return examination;
    }

    public int getGrade() {
        return grade;
    }

    public int getAttempt() {
        return attempt;
    }
}
