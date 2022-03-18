package com.example.campusdirecter.model;

import androidx.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Student extends Person {

    private String seminarGroup;
    private Progression progression;

    public Student(String id, Name name, String seminarGroup, Progression progression) {
        super(id, name);
        this.seminarGroup = seminarGroup;
        this.progression = progression;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student that = (Student) obj;
        return getId().equals(that.getId());
    }
}
