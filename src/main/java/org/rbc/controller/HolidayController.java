package org.rbc.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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


    @PostMapping(value = "/addHoliday")
    public ResponseEntity<HolidayResponse> create(@Valid @RequestBody HolidayRequest holidayRequest) {
        return new ResponseEntity<>(holidayService.addHoliday(holidayRequest),HttpStatus.OK);
    }

    // READ (All)
    @GetMapping(value="/countrylist/{name}")
    public ResponseEntity<List<HolidayResponse>> getListOfFederalHolidays(@PathVariable @Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets allowed") @NotNull String name) {
        List<HolidayResponse> fedHolList=holidayService.getHolidays(name);
        return new ResponseEntity<>(fedHolList, HttpStatus.OK);

    }

/*    // READ (Single)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product details) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(details.getName());
                    product.setPrice(details.getPrice());
                    return ResponseEntity.ok(repository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }*/
}
