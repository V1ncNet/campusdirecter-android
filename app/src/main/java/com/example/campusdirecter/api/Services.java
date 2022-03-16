package com.example.campusdirecter.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.campusdirecter.model.Student;
import com.example.campusdirecter.model.Timetable;
import com.google.gson.Gson;

public class Services {

    Context context;

    public Services(Context context) {
        this.context = context;
    }

    @FunctionalInterface
    public interface StudentResponseListener {
        default void onError(String message) {
        }

        void onResponse(Student student);

    }

    @FunctionalInterface
    public interface TimetableResponseListener {
        default void onError(String message) {
        }

        void onResponse(Timetable timetable);

    }

    public void getStudent(StudentResponseListener responseListener) {

        String url = "https://srv-dev01.campusdirecter.vinado.de/student/0815421337420";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.d("Studentobject: ", response.toString());
                    String studentString = response.toString();
                    Gson gson = new Gson();
                    Student student = gson.fromJson(studentString, Student.class);
                    responseListener.onResponse(student);
                }, error -> {
                    Log.d("someting went wrong: ", error.toString());
                    responseListener.onError("Somthing went wrong");
                });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getTimetable(TimetableResponseListener responseListener) {
        String url = "https://srv-dev01.campusdirecter.vinado.de/timetable?studentId=0815421337420";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.d("Timetableobject: ", response.toString());
                    String timeTableString = response.toString();
                    Gson gson = new Gson();
                    Timetable timeTable = gson.fromJson(timeTableString, Timetable.class);
                    responseListener.onResponse(timeTable);
                }, error -> {
                    Log.d("someting went wrong: ", error.toString());
                    responseListener.onError("Something went wrong");
                });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
