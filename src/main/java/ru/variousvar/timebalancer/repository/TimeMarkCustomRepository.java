package ru.variousvar.timebalancer.repository;

import org.springframework.data.repository.query.Param;
import ru.variousvar.timebalancer.entity.TimeMark;

import java.time.Instant;

public interface TimeMarkCustomRepository {
    /**
     * Finds last exist mark created for selected timing.
     *
     * @param timingId id for distinguish various timings profiles
     *
     * @return mark if it exist, null otherwise
     */
    TimeMark findLast(@Param("timingId") Long timingId);

    /**
     * Checks that for selected timing profile ({@code timingId}) closest to the {@code date} to the left mark exist and opened.
     *
     * @param timingId id for distinguish various timings profiles
     * @param date mark should exist and be opened right before this date
     * @return true, if mark exist and it is opened, false otherwise
     */
    boolean isOpenedBeforeDate(@Param("timingId") Long timingId, Instant date);
}
