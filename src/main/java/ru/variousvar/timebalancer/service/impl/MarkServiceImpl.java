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

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static java.time.Duration.*;

@Service
@Transactional(transactionManager = "transactionManager")
public class MarkServiceImpl implements MarkService {

    @Autowired
    private TimeMarkRepository markRepository;

    @Override
    public Duration countTime(Long timingId, Instant from, Instant to, TemporalUnit unit) {
        List<TimeMark> marks = markRepository.findMarks(from, to, timingId);
        Duration overall = ZERO;

        if (marks.isEmpty()) {
            // no marks between dates, but it maybe a case where dates are between marks
            // so check if there is exist an opened mark directly before from date
            if (markRepository.isOpenedBeforeDate(timingId, from)) {
                overall = overall.plus(between(from, to));

            // so, it means that no opened mark exist at all or only closing mark exist or no marks at all
            } else {
                overall = ZERO;
            }
        } else if (marks.size() == 1) {
            overall = addBounds(overall, marks, from, to);
        } else {
            int openingMarkIdx = marks.get(0).getStart() ? 0 : 1;

            while (openingMarkIdx + 1 < marks.size()) {
                TimeMark openingMark = marks.get(openingMarkIdx);
                TimeMark closingMark = marks.get(openingMarkIdx + 1);

                overall = overall.plus(Duration.between(openingMark.getMark(), closingMark.getMark()));
                openingMarkIdx += 2;
            }

            overall = addBounds(overall, marks, from, to);
        }

        return overall;
    }

    private Duration addBounds(Duration overall, List<TimeMark> marks, Instant from, Instant to) {
        TimeMark first = marks.get(0);
        TimeMark last = marks.get(marks.size() - 1);

        if (!first.getStart()) {
            overall = overall.plus(Duration.between(from, first.getMark()));
        }

        if (last.getStart()) {
            overall = overall.plus(Duration.between(last.getMark(), to));
        }

        return overall;
    }

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
