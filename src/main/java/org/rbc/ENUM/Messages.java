package org.rbc.ENUM;

public enum Messages {

    NOT_VALID_MONTH("not valid month"),
    NO_HOLIDAYS_COUNTRY("holiday does not exist for this country"),
    HOLIDAY_EXIST("holiday already exist"),
    ALL("ALL");
    private String message;

    public String getMessage() {
        return this.message;
    }
    Messages(String message) {
        this.message=message;
    }
}
