package ru.variousvar.timebalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.variousvar.timebalancer.entity.Timing;
import ru.variousvar.timebalancer.service.TimingService;

@RestController
@RequestMapping("/timings")
public class TimingController {

    @Autowired
    private TimingService timingService;

    @GetMapping
    public ResponseEntity<Page<Timing>> getAll(Pageable pageable) {
        return new ResponseEntity<>(timingService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Timing> create(@RequestBody Timing timing) {
        return new ResponseEntity<>(timingService.create(timing), HttpStatus.OK);
    }
}
