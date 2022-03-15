package com.example.campusdirecter.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.campusdirecter.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestAction {

    String url;
    Context context;


    public RequestAction(Context context){
        this.context=context;
    }


    public void getStudent()
    {

        url = "https://srv-dev01.campusdirecter.vinado.de/student/0815421337420";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Studentobject: ", response.toString());


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("someting went wrong: ", error.toString());

                    }
                });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getTimetable()

    {
        url = "https://srv-dev01.campusdirecter.vinado.de/timetable?studentId=0815421337420";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Timetableobject: ", response.toString());


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("someting went wrong: ", error.toString());

                    }
                });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);



    }



}
