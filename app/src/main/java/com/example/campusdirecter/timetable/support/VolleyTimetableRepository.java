package com.example.campusdirecter.timetable.support;

import android.util.Log;

import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.HttpRequest;
import com.example.campusdirecter.http.HttpResponse;
import com.example.campusdirecter.model.Timetable;
import com.example.campusdirecter.timetable.model.TimetableRepository;
import com.example.campusdirecter.timetable.model.TimetableRetrieveCallback;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.URL;

/**
 * @author jureao
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class VolleyTimetableRepository implements TimetableRepository {

    private final HttpClient client;

    public VolleyTimetableRepository(HttpClient client) {
        this.client = client;
    }

    @Override
    public void retrieve(TimetableRetrieveCallback callback) {
        String url = "https://srv-dev01.campusdirecter.vinado.de/timetable?studentId=0815421337420";
        client.get(HttpRequest.sneaky(() -> new URL(url)), new HttpResponse() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.d("Timetableobject: ", response.toString());
                String timeTableString = response.toString();
                Gson gson = new Gson();
                Timetable timeTable = gson.fromJson(timeTableString, Timetable.class);
                callback.onResponse(timeTable);
            }

            @Override
            public void onError(Throwable error) {
                Log.d("someting went wrong: ", error.toString());
                callback.onError("Something went wrong");
            }
        });
    }
}
