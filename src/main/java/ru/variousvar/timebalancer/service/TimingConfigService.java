package ru.variousvar.timebalancer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.variousvar.timebalancer.entity.TimingConfig;

public interface TimingConfigService {
    Page<TimingConfig> findAll(Pageable pageable);
    TimingConfig create(TimingConfig timingConfig);
}
