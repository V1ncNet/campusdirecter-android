package com.example.campusdirecter.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.Predicate;

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
        return Arrays.stream(attempts)
                .filter(byExam(matches(examination)))
                .mapToDouble(Attempt::getGrade)
                .filter(le(4.0f))
                .findAny()
                .isPresent();
    }

    private static Predicate<Attempt> byExam(Function<Examination, Boolean> extractor) {
        return attempt -> extractor.apply(attempt.getExamination());
    }

    private static Function<Examination, Boolean> matches(Examination examination) {
        return self -> Objects.equals(self, examination);
    }

    private static DoublePredicate le(Number number) {
        return value -> value <= number.doubleValue();
    }

    // accumulates current credits
    public int sumCredits() {
        return Arrays.stream(attempts)
                .map(Attempt::getExamination)
                .map(Examination::getCourse)
                .map(Course::getModule)
                .mapToInt(Module::getCredits)
                .sum();
    }
}
