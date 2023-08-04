package com.explore.playground.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@ControllerAdvice
public class PlayGroundExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> additionalInfo = new TreeMap<>();
        List<String> messages = new ArrayList<>();
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for (int i = 0; i < fieldErrorList.size(); i++) {
            FieldError error = fieldErrorList.get(i);
            messages.add(String.format("%s => %s", error.getField(), error.getDefaultMessage()));
        }
        Collections.sort(messages);
        for (int i = 0; i < messages.size(); i++) {
            additionalInfo.put("violation " + (i + 1), messages.get(i));
        }
        return ResponseEntity.badRequest().body(additionalInfo);
    }
}
