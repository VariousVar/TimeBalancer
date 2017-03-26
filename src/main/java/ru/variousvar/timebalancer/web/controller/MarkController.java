package ru.variousvar.timebalancer.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.variousvar.timebalancer.entity.TimeMark;
import ru.variousvar.timebalancer.service.MarkService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

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
    public Map calcTime(@RequestParam("from") Instant from, @RequestParam("to") Instant to, @PathVariable Long timingId) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("time", markService.countTime(timingId, from, to, ChronoUnit.DAYS).getSeconds());

        return map;
    }

    @GetMapping("/{timingId}")
    @JsonView(TimeMark.CommonView.class)
    public Map getMarks(@RequestParam("from") Instant from, @RequestParam("to") Instant to, @PathVariable Long timingId) {
        Map<String, Object> map = new HashMap<>();

        map.put("marks", markService.getMarks(timingId, from, to));

        return map;
    }
}
