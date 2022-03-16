package com.example.campusdirecter.http;

import org.json.JSONObject;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@FunctionalInterface
public interface HttpResponse {

    void onSuccess(JSONObject response);

    default void onError(Throwable error) {
    }
}
