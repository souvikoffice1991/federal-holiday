package org.rbc.ENUM;

public enum Messages {

    NOT_VALID_MONTH("not valid month"),
    NO_HOLIDAYS_COUNTRY("no holiday exist for this country"),
    HOLIDAY_EXIST("holiday already exist"),
    ALL("ALL");
    private String message;
    Messages(String message){
        this.message=message;
    }
}
