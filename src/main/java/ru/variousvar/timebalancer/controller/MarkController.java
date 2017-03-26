package ru.variousvar.timebalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.variousvar.timebalancer.entity.TimeMark;
import ru.variousvar.timebalancer.service.MarkService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

@RestController
@RequestMapping("/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @PostMapping("/now")
    public ResponseEntity newMarkFromNow(@RequestBody TimeMark timeMark) {
        markService.createTimeMarkNow(timeMark.getTiming());

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/date")
    public ResponseEntity newMarkFromDate(@RequestBody TimeMark timeMark) {
        markService.createTimeMarkFromDate(timeMark.getTiming(), timeMark.getMark());

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/time/{timingId}")
    public java.util.Map calcTime(@RequestParam String fromS, @RequestParam String toS, @PathVariable Long timingId) {
        HashMap<String, Object> map = new HashMap<>();
        Instant from = Instant.parse(fromS);
        Instant to = Instant.parse(toS);

        map.put("time", markService.countTime(timingId, from, to, ChronoUnit.DAYS).getSeconds());

        return map;
    }
}
