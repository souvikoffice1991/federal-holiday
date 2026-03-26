package org.rbc.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.rbc.ENUM.Messages;
import org.rbc.data.HolidayRequest;
import org.rbc.data.HolidayResponse;
import org.rbc.entity.Holiday;
import org.rbc.service.HolidayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fedhol")
public class HolidayController {

    HolidayService holidayService;
    public HolidayController(HolidayService holidayService){
        this.holidayService=holidayService;
    }


    @PostMapping(value = "/add-Holiday")
    public ResponseEntity<HolidayResponse> create(@Valid @RequestBody HolidayRequest holidayRequest) {
       return new ResponseEntity<>(holidayService.addOrUpdateHoliday(holidayRequest,Boolean.FALSE),HttpStatus.OK);
    }

    @GetMapping(value="/country-list/{name}")
    public ResponseEntity<List<HolidayResponse>> getListOfFederalHolidays(@PathVariable @Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets allowed") @NotNull String name) {
        return new ResponseEntity<>(holidayService.getHolidays(name), HttpStatus.OK);

    }

    @PutMapping("/update-Holiday")
    public ResponseEntity<HolidayResponse> update(@Valid @RequestBody HolidayRequest holidayRequest) {
       return new ResponseEntity<>(holidayService.addOrUpdateHoliday(holidayRequest,Boolean.TRUE),HttpStatus.OK);
    }


}
