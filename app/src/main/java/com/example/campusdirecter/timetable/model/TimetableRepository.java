package com.example.campusdirecter.timetable.model;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public interface TimetableRepository {

    void retrieve(TimetableRetrieveCallback callback);
}
