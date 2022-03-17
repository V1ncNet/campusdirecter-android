package com.example.campusdirecter.model;

import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Objects;

public class ElectiveModule extends Module {

    public ElectiveModule(String code, int credits, Collection<? extends Course> courses) {
        super(code, credits, courses);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ElectiveModule that = (ElectiveModule) obj;
        return Objects.equals(this.getCode(), that.getCode());
    }
}
