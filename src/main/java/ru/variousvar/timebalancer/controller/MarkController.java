package ru.variousvar.timebalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.variousvar.timebalancer.entity.TimeMark;
import ru.variousvar.timebalancer.repository.TimeMarkRepository;

import java.time.Instant;

@RestController
@RequestMapping("/marks")
public class MarkController {

    @Autowired
    private TimeMarkRepository timeMarkRepository;

    @PostMapping("/check")
    public ResponseEntity newMarkFromNow(@RequestBody TimeMark timeMark) {
        timeMark.setMark(Instant.now());

        timeMarkRepository.save(timeMark);

        return new ResponseEntity(HttpStatus.OK);
    }
}
