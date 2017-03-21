package ru.variousvar.timebalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.variousvar.timebalancer.entity.TimeMark;
import ru.variousvar.timebalancer.service.MarkService;

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
}
