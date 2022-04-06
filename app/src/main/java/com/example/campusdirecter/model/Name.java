package com.example.campusdirecter.model;

import java.util.StringJoiner;
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

    public String getFullName()
    {
        StringJoiner sj = new StringJoiner(" ");
        if (firstName != null) {
            sj.add(firstName);
        }
        if (lastName != null)
        {
            sj.add(lastName);
        }

        return sj.toString();
    }

    public String getInitials()
    {
        if (firstName!=null && lastName !=null){
           return  firstName.charAt(0) + ""  + lastName.charAt(0);
       }

       else{
           return "";
       }
    }
}
