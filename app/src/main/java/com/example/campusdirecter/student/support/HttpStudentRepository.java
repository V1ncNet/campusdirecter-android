package com.example.campusdirecter.student.support;

import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.HttpRequest;
import com.example.campusdirecter.http.HttpResponse;
import com.example.campusdirecter.model.Student;
import com.example.campusdirecter.student.model.StudentRepository;
import com.example.campusdirecter.student.model.StudentRetrieveCallback;
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
public class HttpStudentRepository implements StudentRepository {

    private final HttpClient client;
    private final GsonBuilder gsonBuilder;

    @Override
    public void retrieve(StudentRetrieveCallback callback) {
        String url = "https://srv-dev01.campusdirecter.vinado.de/student/0815421337420";
        client.get(HttpRequest.sneaky(() -> new URL(url)), new RetrieveCallbackAdapter(callback));
    }


    @RequiredArgsConstructor
    private final class RetrieveCallbackAdapter implements HttpResponse {

        private final StudentRetrieveCallback callback;

        @Override
        public void onSuccess(JSONObject response) {
            Gson gson = gsonBuilder.create();
            String studentString = response.toString();

            Student student = gson.fromJson(studentString, Student.class);
            callback.onResponse(student);
        }

        @Override
        public void onError(Throwable error) {
            callback.onError(error);
        }
    }
}
