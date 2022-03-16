package com.example.campusdirecter.student.support;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.campusdirecter.api.SingletonRequestQueue;
import com.example.campusdirecter.model.Student;
import com.example.campusdirecter.student.model.StudentRepository;
import com.example.campusdirecter.student.model.StudentRetrieveCallback;
import com.google.gson.Gson;

/**
 * @author jureao
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class VolleyStudentRepository implements StudentRepository {

    private final Context context;

    public VolleyStudentRepository(Context context) {
        this.context = context;
    }

    @Override
    public void retrieve(StudentRetrieveCallback callback) {
        String url = "https://srv-dev01.campusdirecter.vinado.de/student/0815421337420";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.d("Studentobject: ", response.toString());
                    String studentString = response.toString();
                    Gson gson = new Gson();
                    Student student = gson.fromJson(studentString, Student.class);
                    callback.onResponse(student);
                }, error -> {
                    Log.d("someting went wrong: ", error.toString(), error.fillInStackTrace());
                    callback.onError("Somthing went wrong");
                });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
