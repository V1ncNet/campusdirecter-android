package com.example.campusdirecter.model;

import lombok.Value;

@Value
public class Name {

    String salutation;
    String firstName;
    String lastName;

    public Name(String salutation, String firstName, String lastName) {
        this.salutation = salutation;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return salutation() + " " + firstName + " " + lastName;
    }

    private String salutation() {
        return null == salutation ? "" : salutation;
    }
}
