package com.example.campusdirecter.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.campusdirecter.model.Student;
import com.example.campusdirecter.model.Timetable;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Services {

    String url;
    Context context;
    Student student;


    public Services(Context context){
        this.context=context;
    }

    public interface StudentResponseListener
    {
        void onError(String message);

        void onResponse(Student student);

    }

    public interface TimetableResponseListener
    {
        void onError(String message);

        void onResponse(Timetable timetable);

    }


    public void getStudent(StudentResponseListener responseListener)
    {

        url = "https://srv-dev01.campusdirecter.vinado.de/student/0815421337420";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Studentobject: ", response.toString());
                        String studentString = response.toString();
                        Gson  gson = new Gson();
                        student =  gson.fromJson(studentString, Student.class);
                        responseListener.onResponse(student);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("someting went wrong: ", error.toString());
                        responseListener.onError("Somthing went wrong");

                    }
                });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getTimetable(TimetableResponseListener responseListener)

    {
        url = "https://srv-dev01.campusdirecter.vinado.de/timetable?studentId=0815421337420";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Timetableobject: ", response.toString());
                        String timeTableString = response.toString();
                        Gson  gson = new Gson();
                        Timetable timeTable =  gson.fromJson(timeTableString, Timetable.class);
                        responseListener.onResponse(timeTable);


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("someting went wrong: ", error.toString());
                        responseListener.onError("Something went wrong");

                    }
                });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);



    }



}
