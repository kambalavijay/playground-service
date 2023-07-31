package com.example.testvalidation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@ControllerAdvice
public class SynapseUserExceptionHandler {

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
