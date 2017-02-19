package ru.variousvar.timebalancer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.variousvar.timebalancer.exception.ConcurrentMarkInsertion;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(ConcurrentMarkInsertion.class)
    public ResponseEntity concurrentMarkInsertion(ConcurrentMarkInsertion ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex);
    }
}
