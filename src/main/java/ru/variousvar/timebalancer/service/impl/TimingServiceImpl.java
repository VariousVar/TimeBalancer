package ru.variousvar.timebalancer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.variousvar.timebalancer.entity.Timing;
import ru.variousvar.timebalancer.repository.TimingRepository;
import ru.variousvar.timebalancer.service.TimingService;

@Service
public class TimingServiceImpl implements TimingService {

    @Autowired
    private TimingRepository timingRepository;

    @Override
    public Page<Timing> findAll(Pageable pageable) {
        return timingRepository.findAll(pageable);
    }

    @Override
    public Timing create(Timing timing) {
        return timingRepository.save(timing);
    }
}
