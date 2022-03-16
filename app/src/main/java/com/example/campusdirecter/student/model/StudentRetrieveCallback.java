package com.example.campusdirecter.student.model;

import com.example.campusdirecter.model.Student;

/**
 * @author jureao
 */
@FunctionalInterface
public interface StudentRetrieveCallback {

    void onResponse(Student student);

    default void onError(Throwable error) {
    }
}
