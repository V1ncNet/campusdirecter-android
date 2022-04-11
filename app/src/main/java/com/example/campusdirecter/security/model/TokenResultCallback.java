package com.example.campusdirecter.security.model;

import com.example.campusdirecter.security.support.Result;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public interface TokenResultCallback {

    void onOk(Result<LoggedInUser> result);

    void onUnauthorized(Result<Void> result);
}
