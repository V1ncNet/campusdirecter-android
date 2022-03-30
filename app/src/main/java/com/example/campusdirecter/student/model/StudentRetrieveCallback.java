package com.example.campusdirecter.student.model;

import android.util.Log;

import com.example.campusdirecter.model.Student;

/**
 * @author jureao
 */
@FunctionalInterface
public interface StudentRetrieveCallback {

    void onResponse(Student student);

    default void onError(Throwable error) {
        Log.e(StudentRetrieveCallback.class.getCanonicalName(),
                "Error during student retrieval",
                error);
    }
}
