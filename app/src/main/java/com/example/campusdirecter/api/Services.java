package com.example.campusdirecter.api;

import android.content.Context;

import com.example.campusdirecter.student.model.StudentRepository;
import com.example.campusdirecter.student.model.StudentRetrieveCallback;
import com.example.campusdirecter.student.support.VolleyStudentRepository;
import com.example.campusdirecter.timetable.model.TimetableRepository;
import com.example.campusdirecter.timetable.model.TimetableRetrieveCallback;
import com.example.campusdirecter.timetable.support.VolleyTimetableRepository;

public class Services {

    private final StudentRepository studentRepository;
    private final TimetableRepository timetableRepository;

    public Services(Context context) {
        this.studentRepository = new VolleyStudentRepository(context);
        this.timetableRepository = new VolleyTimetableRepository(context);
    }

    public void getStudent(StudentRetrieveCallback responseListener) {
        studentRepository.retrieve(responseListener);
    }

    public void getTimetable(TimetableRetrieveCallback responseListener) {
        timetableRepository.retrieve(responseListener);
    }
}
