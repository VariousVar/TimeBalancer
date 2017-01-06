package ru.variousvar.timebalancer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.variousvar.timebalancer.entity.Profile;

public interface ProfileService {
    Page<Profile> findAll(Pageable pageable);
    Profile create(Profile profile);
}
