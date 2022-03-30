package com.example.campusdirecter.student.model;

import com.example.campusdirecter.common.ErrorCallback;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public interface StudentRepository {

    default void retrieve(StudentRetrieveCallback callback) {
        retrieve(callback, throwable -> {
        });
    }

    void retrieve(StudentRetrieveCallback callback, ErrorCallback error);
}
