package com.example.campusdirecter.model;

public class Location {
    private String building;
    private String room;

    public Location(String building, String room) {
        this.building = building;
        this.room = room;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }

    public String getFullRoom() {
        return building + "." + room;
    }
}
