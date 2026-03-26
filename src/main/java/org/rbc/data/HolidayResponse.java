package org.rbc.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HolidayResponse{
    String name;
    String date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String state;


}
