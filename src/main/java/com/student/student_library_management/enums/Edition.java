package com.student.student_library_management.enums;

public enum Edition {
    FIRST("1st"),
    SECOND("2nd"),
    THIRD("3rd"),
    FOURTH("4th"),
    FIFTH("5th"),
    SIXTH("6th"),
    SEVENTH("7th"),
    EIGHTH("8th"),
    NINTH("9th"),
    TENTH("10th");

    private final String displayValue;

    Edition(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}