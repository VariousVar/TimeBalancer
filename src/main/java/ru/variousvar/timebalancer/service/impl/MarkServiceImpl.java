package ru.variousvar.timebalancer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.variousvar.timebalancer.MarkCreationStatus;
import ru.variousvar.timebalancer.entity.TimeMark;
import ru.variousvar.timebalancer.entity.Timing;
import ru.variousvar.timebalancer.exception.InvalidMarkException;
import ru.variousvar.timebalancer.repository.TimeMarkRepository;
import ru.variousvar.timebalancer.service.MarkService;
import ru.variousvar.timebalancer.validation.ValidityResult;

import java.time.Instant;

@Service
@Transactional(transactionManager = "transactionManager")
public class MarkServiceImpl implements MarkService {

    @Autowired
    private TimeMarkRepository markRepository;

    @Override
    @Transactional(transactionManager = "transactionManager")
    public TimeMark getLast(Timing timing) {
        return markRepository.findLast(timing.getId());
    }

    @Override
    @Transactional(transactionManager = "transactionManager")
    public void createTimeMarkNow(Timing timing) {
        createTimeMarkFromDate(timing, Instant.now());
    }

    @Override
    @Transactional(transactionManager = "transactionManager")
    public void createTimeMarkFromDate(Timing timing, Instant date) {
        TimeMark last = getLast(timing);

        validateNewMark(last, date);

        TimeMark newMark = new TimeMark();
        newMark.setTiming(timing);
        newMark.setMark(date);
        newMark.setStart(last == null || !last.getStart());

        markRepository.save(newMark);
    }

    private void validateNewMark(TimeMark last, Instant date) {
        if (last != null && date.compareTo(last.getMark()) <= 0) {
            throw new InvalidMarkException("Invalid parameters", MarkCreationStatus.INVALID)
                    .addValidationResult(new ValidityResult("date", date, false, "Date should lead after last mark"));
        }
    }

    @Override
    public void createPeriod(Instant from, Instant to, Timing timing) {
        throw new UnsupportedOperationException();
    }
}
