package com.example.campusdirecter.format;

import java.util.Locale;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@FunctionalInterface
public interface Printer<T> {

    String print(T source, Locale locale);
}
