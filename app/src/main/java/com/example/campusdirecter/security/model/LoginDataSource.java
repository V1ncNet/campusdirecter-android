package com.example.campusdirecter.security.model;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public interface LoginDataSource {

    void login(String username, String password, LoginResultCallback callback);

    void logout();
}
