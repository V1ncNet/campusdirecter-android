package com.example.campusdirecter.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(exclude = {"student"})
@EqualsAndHashCode(exclude = {"student"})
public class Progression {

    Student student;
    int semester;
    Attempt[] attempts;

    public Progression(Student student, int semester, Attempt[] attempts) {
        this.student = student;
        this.semester = semester;
        this.attempts = attempts;
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

    // checks if an examination was attempted and the grade is better than 4.0
    public boolean isPassed(Examination examination) {
        return Arrays.stream(attempts)
                .filter(byExam(matches(examination)))
                .mapToDouble(Attempt::getGrade)
                .filter(le(4.0f))
                .findAny()
                .isPresent();
    }

    // accumulates current credits
    public int sumCredits() {
        return Arrays.stream(attempts)
                .map(Attempt::getExamination)
                .filter(this::isPassed)
                .map(Examination::getCourse)
                .map(Course::getModule)
                .mapToInt(Module::getCredits)
                .sum();
    }
}
