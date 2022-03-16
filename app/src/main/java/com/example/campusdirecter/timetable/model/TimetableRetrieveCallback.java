package com.example.campusdirecter.timetable.model;

import com.example.campusdirecter.model.Timetable;

/**
 * @author jureao
 */
@FunctionalInterface
public interface TimetableRetrieveCallback {

    void onResponse(Timetable timetable);

    default void onError(Throwable error) {
    }
}
