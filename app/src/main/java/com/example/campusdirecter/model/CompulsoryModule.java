package com.example.campusdirecter.model;

import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Objects;

public class CompulsoryModule extends Module {

    public CompulsoryModule(String code, int credits, Collection<? extends Course> courses) {
        super(code, credits, courses);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompulsoryModule that = (CompulsoryModule) obj;
        return Objects.equals(this.getCode(), that.getCode());
    }
}
