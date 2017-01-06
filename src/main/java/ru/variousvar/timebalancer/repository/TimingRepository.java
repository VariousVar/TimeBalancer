package ru.variousvar.timebalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.variousvar.timebalancer.entity.Timing;

@Repository
public interface TimingRepository extends JpaRepository<Timing, Long> {
}
