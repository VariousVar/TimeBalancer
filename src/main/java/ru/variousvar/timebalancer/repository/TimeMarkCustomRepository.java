package ru.variousvar.timebalancer.repository;

import org.springframework.data.repository.query.Param;
import ru.variousvar.timebalancer.entity.TimeMark;

import java.time.Instant;

public interface TimeMarkCustomRepository {
    TimeMark findLast(@Param("timingId") Long timingId);
}
