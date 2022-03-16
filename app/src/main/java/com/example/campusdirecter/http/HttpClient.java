package com.example.campusdirecter.http;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public interface HttpClient {

    void get(HttpRequest request, HttpResponse response);
}
