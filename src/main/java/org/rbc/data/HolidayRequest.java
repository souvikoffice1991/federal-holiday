package org.rbc.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rbc.validator.CorrectDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayRequest {
    @NotNull(message = "Only alphabets allowed")
    String name;
    @CorrectDate
    String date;
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets allowed")
    String country;
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets allowed")
    String state;
}
