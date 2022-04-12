package com.example.campusdirecter.security.model;

import android.util.Log;

import com.example.campusdirecter.security.support.Result;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@FunctionalInterface
public interface LoginResultCallback {

    void onResponse(Result<LoggedInUser> result);

    default void onError(Result<Void> result) {
        Log.e(LoginResultCallback.class.getCanonicalName(), result.toString());
    }
}
