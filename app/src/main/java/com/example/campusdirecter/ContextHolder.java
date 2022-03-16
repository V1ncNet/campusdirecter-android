package com.example.campusdirecter;

import android.content.Context;

import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.VolleyHttpClient;
import com.example.campusdirecter.student.model.StudentRepository;
import com.example.campusdirecter.student.support.HttpStudentRepository;
import com.example.campusdirecter.timetable.model.TimetableRepository;
import com.example.campusdirecter.timetable.support.HttpTimetableRepository;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class ContextHolder {

    private static ContextHolder instance = null;
    private static Context context;

    private HttpClient client;

    private ContextHolder(Context context) {
        ContextHolder.context = context;
        this.client = getHttpClient();
    }

    public static ContextHolder getInstance(Context context) {
        if (instance == null) {
            synchronized (ContextHolder.class) {
                instance = new ContextHolder(context);
            }
        }
        return instance;
    }

    public HttpClient getHttpClient() {
        if (null == client) {
            client = VolleyHttpClient.getInstance(context);
        }
        return client;
    }

    public StudentRepository getStudentRepository() {
        HttpClient client = getHttpClient();
        return new HttpStudentRepository(client);
    }

    public TimetableRepository getTimetableRepository() {
        HttpClient client = getHttpClient();
        return new HttpTimetableRepository(client);
    }
}
