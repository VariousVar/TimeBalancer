package ru.variousvar.timebalancer.service;

import ru.variousvar.timebalancer.entity.TimeMark;

import java.time.Instant;

public interface MarkService {
    TimeMark getLast();
    void createTimeMark(TimeMark mark);
    void createPeriod(Instant from, Instant to, long timingId);
}
