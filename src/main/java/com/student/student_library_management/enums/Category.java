package com.student.student_library_management.enums;

public enum Category {
    FICTION("Fiction"),
    NON_FICTION("Non-Fiction"),
    SCIENCE("Science"),
    MATHEMATICS("Mathematics"),
    COMPUTER_SCIENCE("Computer Science"),
    PROGRAMMING("Programming"),
    ENGINEERING("Engineering"),
    MEDICAL("Medical"),
    HISTORY("History"),
    GEOGRAPHY("Geography"),
    POLITICAL_SCIENCE("Political Science"),
    ECONOMICS("Economics"),
    COMMERCE("Commerce"),
    ACCOUNTING("Accounting"),
    MANAGEMENT("Management"),
    BUSINESS("Business"),
    LITERATURE("Literature"),
    POETRY("Poetry"),
    DRAMA("Drama"),
    BIOGRAPHY("Biography"),
    AUTOBIOGRAPHY("Autobiography"),
    RELIGION("Religion"),
    PHILOSOPHY("Philosophy"),
    PSYCHOLOGY("Psychology"),
    SOCIOLOGY("Sociology"),
    ART("Art"),
    MUSIC("Music"),
    SPORTS("Sports"),
    TRAVEL("Travel"),
    COOKING("Cooking"),
    CHILDREN("Children"),
    COMICS("Comics"),
    MAGAZINE("Magazine"),
    NEWSPAPER("Newspaper"),
    DICTIONARY("Dictionary"),
    ENCYCLOPEDIA("Encyclopedia"),
    TEXTBOOK("Textbook"),
    REFERENCE("Reference"),
    COMPETITIVE_EXAM("Competitive Exam"),
    GENERAL("General"),
    OTHER("Other");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category fromDisplayName(String displayName) {
        for (Category category : Category.values()) {
            if (category.displayName.equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category: " + displayName);
    }
}