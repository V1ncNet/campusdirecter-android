package com.example.campusdirecter.student.model;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public interface StudentRepository {

    void retrieve(StudentRetrieveCallback callback);
}
