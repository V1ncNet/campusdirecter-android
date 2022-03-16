package com.example.campusdirecter.timetable.support;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.campusdirecter.api.SingletonRequestQueue;
import com.example.campusdirecter.model.Timetable;
import com.example.campusdirecter.timetable.model.TimetableRepository;
import com.example.campusdirecter.timetable.model.TimetableRetrieveCallback;
import com.google.gson.Gson;

/**
 * @author jureao
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class VolleyTimetableRepository implements TimetableRepository {

    private final Context context;

    public VolleyTimetableRepository(Context context) {
        this.context = context;
    }

    @Override
    public void retrieve(TimetableRetrieveCallback callback) {
        String url = "https://srv-dev01.campusdirecter.vinado.de/timetable?studentId=0815421337420";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.d("Timetableobject: ", response.toString());
                    String timeTableString = response.toString();
                    Gson gson = new Gson();
                    Timetable timeTable = gson.fromJson(timeTableString, Timetable.class);
                    callback.onResponse(timeTable);
                }, error -> {
                    Log.d("someting went wrong: ", error.toString());
                    callback.onError("Something went wrong");
                });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
