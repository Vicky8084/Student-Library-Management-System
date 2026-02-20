package com.student.student_library_management.enums;

public enum BloodGroup {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String displayValue;

    BloodGroup(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    // Helper method to get enum from display value
    public static BloodGroup fromDisplayValue(String displayValue) {
        for (BloodGroup bg : BloodGroup.values()) {
            if (bg.displayValue.equals(displayValue)) {
                return bg;
            }
        }
        throw new IllegalArgumentException("Invalid blood group: " + displayValue);
    }
}
