package com.example.campusdirecter.timetable.ui;

import com.example.campusdirecter.model.Lecture;

import lombok.Value;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@Value
public class PositionableLecture {

    Lecture lecture;
    int position;
}
