package com.loginManagement.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public ResponseEntity<String> handelRecordNotFoundException(RecordNotFoundException recordNotFoundException){
        return new ResponseEntity<>(recordNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
