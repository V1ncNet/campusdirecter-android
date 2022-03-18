package com.example.campusdirecter.timetable.support;

import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.HttpRequest;
import com.example.campusdirecter.http.HttpResponse;
import com.example.campusdirecter.model.Timetable;
import com.example.campusdirecter.timetable.model.TimetableRepository;
import com.example.campusdirecter.timetable.model.TimetableRetrieveCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.net.URL;

import lombok.RequiredArgsConstructor;

/**
 * @author jureao
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class HttpTimetableRepository implements TimetableRepository {

    private final HttpClient client;
    private final GsonBuilder gsonBuilder;

    @Override
    public void retrieve(TimetableRetrieveCallback callback) {
        String url = "https://srv-dev01.campusdirecter.vinado.de/timetable";
        client.get(HttpRequest.sneaky(() -> new URL(url)), new RetrieveCallbackAdapter(callback));
    }


    @RequiredArgsConstructor
    private final class RetrieveCallbackAdapter implements HttpResponse {

        private final TimetableRetrieveCallback callback;

        @Override
        public void onSuccess(JSONObject response) {
            Gson gson = gsonBuilder.create();
            String timeTableString = response.toString();

            Timetable timeTable = gson.fromJson(timeTableString, Timetable.class);
            callback.onResponse(timeTable);
        }

        @Override
        public void onError(Throwable error) {
            callback.onError(error);
        }
    }
}
