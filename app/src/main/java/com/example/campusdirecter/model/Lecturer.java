package com.example.campusdirecter.model;

import androidx.annotation.Nullable;

public class Lecturer extends Person {

    public Lecturer(String id, Name name) {
        super(id, name);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Lecturer that = (Lecturer) obj;
        return getId().equals(that.getId());
    }
}
