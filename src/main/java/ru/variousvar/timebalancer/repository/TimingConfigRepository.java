package ru.variousvar.timebalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.variousvar.timebalancer.entity.TimingConfig;

public interface TimingConfigRepository extends JpaRepository<TimingConfig, Long> {
}
