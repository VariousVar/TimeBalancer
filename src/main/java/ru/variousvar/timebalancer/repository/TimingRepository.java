package ru.variousvar.timebalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.variousvar.timebalancer.entity.Timing;

public interface TimingRepository extends JpaRepository<Timing, Long> {
}
