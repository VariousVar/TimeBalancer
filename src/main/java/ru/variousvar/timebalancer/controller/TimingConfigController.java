package ru.variousvar.timebalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.variousvar.timebalancer.entity.TimingConfig;
import ru.variousvar.timebalancer.service.TimingConfigService;

@RestController
@RequestMapping("/timingConfigs")
public class TimingConfigController {

    @Autowired
    private TimingConfigService timingConfigService;

    @GetMapping
    public ResponseEntity<Page<TimingConfig>> getAll(Pageable pageable) {
        return new ResponseEntity<>(timingConfigService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimingConfig> create(@RequestBody TimingConfig timingConfig) {
        return new ResponseEntity<>(timingConfigService.create(timingConfig), HttpStatus.OK);
    }
}
