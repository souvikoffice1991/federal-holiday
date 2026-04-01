package org.rbc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.rbc.data.HolidayRequest;
import org.rbc.data.HolidayResponse;
import org.rbc.service.HolidayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Holiday", description = "the Holiday Api")
@RequestMapping("/api/fedhol")
public class HolidayController {

    HolidayService holidayService;
    public HolidayController(HolidayService holidayService){
        this.holidayService=holidayService;
    }


    @Operation(
            summary = "Creates a new holiday",
            description = "Creates a new holiday for a country, if no country is provided creates holiday for every country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping(value = "/add-Holiday")
    public ResponseEntity<HolidayResponse> create(@Valid @RequestBody HolidayRequest holidayRequest) {
       return new ResponseEntity<>(holidayService.addOrUpdateHoliday(holidayRequest,Boolean.FALSE),HttpStatus.OK);
    }

    @Operation(
            summary = "Gets list of holiday for a country",
            description = "Sends list of holiday for the input country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value="/country-list/{name}")
    public ResponseEntity<List<HolidayResponse>> getListOfFederalHolidays(@PathVariable @Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets allowed") @NotNull String name) {
        return new ResponseEntity<>(holidayService.getHolidays(name), HttpStatus.OK);

    }

    @PutMapping("/update-Holiday")
    public ResponseEntity<HolidayResponse> update(@Valid @RequestBody HolidayRequest holidayRequest) {
       return new ResponseEntity<>(holidayService.addOrUpdateHoliday(holidayRequest,Boolean.TRUE),HttpStatus.OK);
    }


}
