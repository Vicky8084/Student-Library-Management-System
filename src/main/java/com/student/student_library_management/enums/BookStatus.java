package com.student.student_library_management.enums;

public enum BookStatus {
    AVAILABLE("Available"),
    ISSUED("Issued"),
    RESERVED("Reserved"),
    UNDER_MAINTENANCE("Under Maintenance"),
    LOST("Lost"),
    DAMAGED("Damaged"),
    DISCARDED("Discarded"),
    ON_HOLD("On Hold"),
    IN_TRANSIT("In Transit"),
    REFERENCE_ONLY("Reference Only"),
    MISSING("Missing"),
    TO_BE_REPAIRED("To Be Repaired"),
    ORDERED("Ordered"),
    PROCESSING("Processing");

    private final String displayValue;

    BookStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static BookStatus fromDisplayValue(String displayValue) {
        for (BookStatus status : BookStatus.values()) {
            if (status.displayValue.equalsIgnoreCase(displayValue)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid book status: " + displayValue);
    }
}