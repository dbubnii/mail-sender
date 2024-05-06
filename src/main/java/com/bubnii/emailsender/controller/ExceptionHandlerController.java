package com.bubnii.emailsender.controller;

import com.bubnii.emailsender.exception.WrongRequestFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(WrongRequestFormatException.class)
    public ResponseEntity<String> handleWrongRequestFormatException(WrongRequestFormatException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
