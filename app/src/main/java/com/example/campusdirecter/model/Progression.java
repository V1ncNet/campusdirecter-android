package com.example.campusdirecter.model;

import java.util.Objects;

public class Progression {
    private Student student;
    private int semester;
    private Attempt[] attempts;

    public Progression(Student student, int semester, Attempt[] attempts) {
        this.student = student;
        this.semester = semester;
        this.attempts = attempts;
    }

    public Student getStudent() {
        return student;
    }

    public int getSemester() {
        return semester;
    }

    public Attempt[] getAttempts() {
        return attempts;
    }

    // checks if an examination was attempted and the grade is better than 4.0
    public boolean isPassed(Examination examination) {
        for (Attempt t : attempts) {
            if (Objects.equals(t.getExamination(), examination) && t.getGrade() <= 4.0) {
                return true;
            }
        }
        return false;
    }

    // accumulates current credits
    public int sumCredits() {
        int res = 0;
        for (Attempt t : attempts) {
            res += t.getExamination().getCourse().getModule().getCredits();
        }
        return res;
    }
}
