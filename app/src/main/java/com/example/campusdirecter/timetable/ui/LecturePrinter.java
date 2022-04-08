package com.example.campusdirecter.timetable.ui;

import static java.time.format.FormatStyle.SHORT;

import com.example.campusdirecter.format.Printer;
import com.example.campusdirecter.model.Lecture;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class LecturePrinter implements Printer<Lecture> {

    private static LecturePrinter instance;

    @Override
    public String print(Lecture source, Locale locale) {
        return source.getSummary() + "\n"
                + format(source.getStartTime(), locale) + " - "
                + format(source.getEndTime(), locale);
    }

    private String format(LocalTime time, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(SHORT)
                .withLocale(locale);
        return formatter.format(time);
    }

    public static LecturePrinter instance() {
        if (null == instance) {
            synchronized (LecturePrinter.class) {
                instance = new LecturePrinter();
            }
        }

        return instance;
    }
}
