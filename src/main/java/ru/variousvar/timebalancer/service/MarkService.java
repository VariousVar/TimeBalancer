package ru.variousvar.timebalancer.service;

import ru.variousvar.timebalancer.entity.TimeMark;
import ru.variousvar.timebalancer.entity.Timing;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;

public interface MarkService {

    Duration countTime(Long timingId, Instant from, Instant to, TemporalUnit unit);

    /**
     * Peek last (ordered by date) mark corresponded to timing.
     * @param timing
     * @return last by date existed time mark.
     */
    TimeMark getLast(Timing timing);

    /**
     * Creates new time mark from current time. It closes/opens period due to last timing registered mark.
     * @param timing
     */
    void createTimeMarkNow(Timing timing);

    /**
     * Creates new time mark from selected date. It closes/opens period due to last timing registered mark.
     * @param timing
     */
    void createTimeMarkFromDate(Timing timing, Instant date);

    /**
     * Insert not a single mark, but a pair of marks: opening mark and closing mark. It could be used to register a
     * future period or a forgotten  period.
     *
     * @param from period start
     * @param to period end
     * @param timing
     */
    void createPeriod(Instant from, Instant to, Timing timing);
}
