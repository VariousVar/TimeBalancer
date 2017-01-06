package ru.variousvar.timebalancer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.variousvar.timebalancer.entity.Profile;
import ru.variousvar.timebalancer.repository.ProfileRepository;
import ru.variousvar.timebalancer.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Profile> findAll(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }
}
