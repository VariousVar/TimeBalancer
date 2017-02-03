package ru.variousvar.timebalancer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.variousvar.timebalancer.entity.TimeMark;
import ru.variousvar.timebalancer.entity.Timing;
import ru.variousvar.timebalancer.repository.TimeMarkRepository;
import ru.variousvar.timebalancer.service.MarkService;

import java.time.Instant;

@Service
@Transactional
public class MarkServiceImpl implements MarkService {

    @Autowired
    private TimeMarkRepository markRepository;

    @Override
    public TimeMark getLast(Timing timing) {
        return markRepository.findLast(timing.getId());
    }

    @Override
    public void createTimeMarkNow(Timing timing) {
        createTimeMarkFromDate(timing, Instant.now());
    }

    @Override
    public void createTimeMarkFromDate(Timing timing, Instant date) {
        TimeMark last = getLast(timing);

        TimeMark newMark = new TimeMark();
        newMark.setTiming(timing);
        newMark.setMark(date);
        newMark.setStart(last == null || !last.getStart());

        markRepository.save(newMark);
    }

    @Override
    public void createPeriod(Instant from, Instant to, Timing timing) {
        throw new UnsupportedOperationException();
    }
}
