package ru.variousvar.timebalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.variousvar.timebalancer.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
