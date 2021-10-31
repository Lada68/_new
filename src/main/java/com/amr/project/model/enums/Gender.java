package com.amr.project.model.enums;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN("UNKNOWN");
    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}