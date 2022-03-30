package com.example.campusdirecter.common;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@FunctionalInterface
public interface ErrorCallback {

    void onError(Throwable throwable);
}
