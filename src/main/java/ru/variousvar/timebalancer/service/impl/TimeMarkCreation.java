package ru.variousvar.timebalancer.service.impl;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.variousvar.timebalancer.entity.Timing;
import ru.variousvar.timebalancer.exception.ConcurrentMarkInsertion;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class TimeMarkCreation {
    private Set<Long> timings = ConcurrentHashMap.newKeySet();

    @Before("execution(* ru.variousvar.timebalancer.service.MarkService.getLast(ru.variousvar.timebalancer.entity.Timing)) && args(timing))")
    public void beforeLastMarkRetrieving(Timing timing) {
        System.out.println("check timing lock");
        if (timings.contains(timing.getId()))
            throw new ConcurrentMarkInsertion("Timing '" + timing.getId() + "' already updated by someone");
    }

    @AfterReturning("execution(* ru.variousvar.timebalancer.service.MarkService.getLast(ru.variousvar.timebalancer.entity.Timing)) && args(timing))")
    public void lockTiming(Timing timing) {
        System.out.println("lock timing");
        timings.add(timing.getId());
    }

    @After(value = "execution (* ru.variousvar.timebalancer.service.MarkService.createTimeMarkFromDate(ru.variousvar.timebalancer.entity.Timing, java.time.Instant)) && args(timing, date))",
            argNames = "timing,date")
    public void unlockTiming(Timing timing, Instant date) {
        System.out.println("after create");
    }
}
