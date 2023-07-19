package com.minime.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
class UrlNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<Object> employeeNotFoundHandler(UrlNotFoundException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.NOT_FOUND.value());
        data.put("message", ex.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
