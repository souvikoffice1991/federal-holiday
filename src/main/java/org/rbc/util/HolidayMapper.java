package org.rbc.util;

import org.rbc.ENUM.Months;
import org.rbc.data.HolidayResponse;
import org.rbc.entity.Holiday;

import java.util.function.Function;

public class HolidayMapper implements Function<Holiday, HolidayResponse> {

    @Override
    public HolidayResponse apply(Holiday holiday) {
        return HolidayResponse.builder().name(holiday.getName()).date(getDate(holiday.getDate())).state(holiday.getState()).build();
    }

    private String getDate(String date){
        String[] dateSplit=date.split("-");
        StringBuilder responseDate= new StringBuilder();
        responseDate.append(Months.getName(Integer.valueOf(dateSplit[0]))).append(",").append(dateSplit[1]);
        return responseDate.toString();
    }
}
