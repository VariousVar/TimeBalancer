package ru.variousvar.timebalancer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.variousvar.timebalancer.entity.Timing;

public interface TimingService {
    Page<Timing> findAll(Pageable pageable);
    Timing create(Timing timing);
}
