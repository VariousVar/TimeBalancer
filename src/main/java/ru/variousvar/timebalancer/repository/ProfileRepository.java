package ru.variousvar.timebalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.variousvar.timebalancer.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
