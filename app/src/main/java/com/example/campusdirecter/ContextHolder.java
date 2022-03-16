package com.example.campusdirecter;

import android.content.Context;

import com.example.campusdirecter.student.model.StudentRepository;
import com.example.campusdirecter.student.support.VolleyStudentRepository;
import com.example.campusdirecter.timetable.model.TimetableRepository;
import com.example.campusdirecter.timetable.support.VolleyTimetableRepository;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class ContextHolder {

    private static ContextHolder instance = null;

    private ContextHolder() {
    }

    public static ContextHolder getInstance() {
        if (instance == null) {
            synchronized (ContextHolder.class) {
                instance = new ContextHolder();
            }
        }
        return instance;
    }

    public StudentRepository getStudentRepository(Context context) {
        return new VolleyStudentRepository(context);
    }

    public TimetableRepository getTimetableRepository(Context context) {
        return new VolleyTimetableRepository(context);
    }
}
