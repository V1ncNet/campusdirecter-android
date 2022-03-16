package com.example.campusdirecter.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.campusdirecter.model.Student;
import com.example.campusdirecter.model.Timetable;
import com.example.campusdirecter.student.model.StudentRetrieveCallback;
import com.example.campusdirecter.timetable.model.TimetableRetrieveCallback;
import com.google.gson.Gson;

public class Services {

    Context context;

    public Services(Context context) {
        this.context = context;
    }

    public void getStudent(StudentRetrieveCallback responseListener) {

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

    public void getTimetable(TimetableRetrieveCallback responseListener) {
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
