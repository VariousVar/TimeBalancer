package ru.variousvar.timebalancer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.variousvar.timebalancer.exception.ConcurrentMarkInsertion;

import java.util.HashMap;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(ConcurrentMarkInsertion.class)
    public ResponseEntity concurrentMarkInsertion(ConcurrentMarkInsertion ex) {
        HashMap<Object, Object> props = new HashMap<>();
        props.put("success", false);
        props.put("message", ex.getMessage());
        props.put("reason", ex.getStatus());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(props);
    }
}
