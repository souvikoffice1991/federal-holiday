package org.rbc.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {




        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
            log.error("validation error thrown");
            Map<String, String> errors = new HashMap<>();
            StringBuilder message = new StringBuilder();
            ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).forEach(def->message.append(def));

            return new ResponseEntity<>(message.toString()
                    ,HttpStatus.BAD_REQUEST);
           // Returns a JSON map of field errors

        }
    }

