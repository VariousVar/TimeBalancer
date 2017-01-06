package ru.variousvar.timebalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.variousvar.timebalancer.entity.TimingConfig;

@Repository
public interface TimingConfigRepository extends JpaRepository<TimingConfig, Long> {
}
