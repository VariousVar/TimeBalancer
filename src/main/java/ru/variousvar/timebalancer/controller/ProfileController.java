package ru.variousvar.timebalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.variousvar.timebalancer.entity.Profile;
import ru.variousvar.timebalancer.repository.ProfileRepository;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping
    public ResponseEntity<List<Profile>> getAll() {
        return new ResponseEntity<>(profileRepository.findAll(), HttpStatus.OK);
    }
}
