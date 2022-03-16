package com.example.campusdirecter.student.support;

import android.util.Log;

import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.HttpRequest;
import com.example.campusdirecter.http.HttpResponse;
import com.example.campusdirecter.model.Student;
import com.example.campusdirecter.student.model.StudentRepository;
import com.example.campusdirecter.student.model.StudentRetrieveCallback;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.URL;

/**
 * @author jureao
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class HttpStudentRepository implements StudentRepository {

    private final HttpClient client;

    public HttpStudentRepository(HttpClient client) {
        this.client = client;
    }

    @Override
    public void retrieve(StudentRetrieveCallback callback) {
        String url = "https://srv-dev01.campusdirecter.vinado.de/student/0815421337420";
        client.get(HttpRequest.sneaky(() -> new URL(url)), new RetrieveCallbackAdapter(callback));
    }


    private static final class RetrieveCallbackAdapter implements HttpResponse {

        private final StudentRetrieveCallback callback;

        public RetrieveCallbackAdapter(StudentRetrieveCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onSuccess(JSONObject response) {
            Log.d("Studentobject: ", response.toString());
            String studentString = response.toString();
            Gson gson = new Gson();
            Student student = gson.fromJson(studentString, Student.class);
            callback.onResponse(student);
        }

        @Override
        public void onError(Throwable error) {
            Log.d("someting went wrong: ", error.toString(), error.fillInStackTrace());
            callback.onError("Somthing went wrong");
        }
    }
}
