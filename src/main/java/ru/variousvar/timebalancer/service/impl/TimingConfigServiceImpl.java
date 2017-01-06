package ru.variousvar.timebalancer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.variousvar.timebalancer.entity.TimingConfig;
import ru.variousvar.timebalancer.repository.TimingConfigRepository;
import ru.variousvar.timebalancer.service.TimingConfigService;

@Service
public class TimingConfigServiceImpl implements TimingConfigService {

    @Autowired
    private TimingConfigRepository timingConfigRepository;

    @Override
    public Page<TimingConfig> findAll(Pageable pageable) {
        return timingConfigRepository.findAll(pageable);
    }

    @Override
    public TimingConfig create(TimingConfig timingConfig) {
        return timingConfigRepository.save(timingConfig);
    }
}
