package ru.variousvar.timebalancer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.variousvar.timebalancer.entity.Profile;
import ru.variousvar.timebalancer.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<Page<Profile>> getAll(Pageable pageable) {
        return new ResponseEntity<>(profileService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        return new ResponseEntity<>(profileService.create(profile), HttpStatus.OK);
    }
}
