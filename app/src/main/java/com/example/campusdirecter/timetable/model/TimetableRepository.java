package com.example.campusdirecter.timetable.model;

import com.example.campusdirecter.common.ErrorCallback;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public interface TimetableRepository {

    default void retrieve(TimetableRetrieveCallback callback) {
        retrieve(callback, throwable -> {
        });
    }

    void retrieve(TimetableRetrieveCallback callback, ErrorCallback error);
}
