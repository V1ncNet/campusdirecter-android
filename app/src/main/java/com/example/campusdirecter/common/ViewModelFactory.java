package com.example.campusdirecter.common;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.campusdirecter.ServiceLocator;
import com.example.campusdirecter.timetable.ui.TimetableModel;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ServiceLocator serviceLocator;

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TimetableModel.class)) {
            return (T) new TimetableModel(serviceLocator.getTimetableRepository());
        }

        throw new IllegalArgumentException();
    }
}
