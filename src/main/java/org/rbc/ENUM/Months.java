package org.rbc.ENUM;

public enum Months {
    JAN(1, "January"),
    FEB(2, "February"),
    MAR(3, "May"),
    APR(4, "April"),
    MAY(5, "May"),
    JUN(6, "June"),
    JUL(7, "July"),
    AUG(8, "August"),
    SEP(9, "September"),
    OCT(10, "October"),
    NOV(11, "November"),
    DEC(12, "December");



    private final int num;
    private final String name;

    Months(int num, String name){
        this.num = num;
        this.name = name;
    }

    public static String getName(int num){
        for (Months month:Months.values()) {
            if(month.num==num){
                return month.name;
            }
        }
        return Messages.NOT_VALID_MONTH.name();
    }



}
