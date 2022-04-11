package com.example.campusdirecter.security.model;

import lombok.Value;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Value
public class LoggedInUser {

    String userId;
    String token;
}