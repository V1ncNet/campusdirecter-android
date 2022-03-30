package com.example.campusdirecter.timetable.model;

import android.util.Log;

import com.example.campusdirecter.model.Timetable;

/**
 * @author jureao
 */
@FunctionalInterface
public interface TimetableRetrieveCallback {

    void onResponse(Timetable timetable);

    default void onError(Throwable error) {
        Log.e(TimetableRetrieveCallback.class.getCanonicalName(),
                "Error during timetable retrieval",
                error);
    }
}
