package com.example.campusdirecter.model;

public class Progression {
    private Student student;
    private int semester;
    private Try[] tries;

    public Progression(Student student, int semester, Try[] tries) {
        this.student = student;
        this.semester = semester;
        this.tries = tries;
    }

    public Student getStudent() {
        return student;
    }

    public int getSemester() {
        return semester;
    }

    public Try[] getTries() {
        return tries;
    }

    // checks if an examination was attempted and the grade is better than 4.0
    public boolean isPassed(Examination examination) {
        boolean res = false;
        for (Try t : tries) {
            if (t.getExamination().getCode() == examination.getCode() && t.getGrade() <= 4.0)
                res = true;
        }
        return res;
    }

    // accumulates current credits
    public int sumCredits() {
        int res = 0;
        for (Try t : tries) {
            res += t.getExamination().getCourse().getModule().getCredits();
        }
        return res;
    }
}
