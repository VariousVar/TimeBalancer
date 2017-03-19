package ru.variousvar.timebalancer.service.impl;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.variousvar.timebalancer.entity.Timing;
import ru.variousvar.timebalancer.exception.ConcurrentMarkInsertion;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class TimeMarkCreation {
    private Set<Long> timings = ConcurrentHashMap.newKeySet();

    @Before(value = "execution(* ru.variousvar.timebalancer.service.MarkService.createTimeMark*(ru.variousvar.timebalancer.entity.Timing, ..)) && args(timing, ..))")
    public void beforeLastMarkRetrievingNow(Timing timing) {
        if (timings.contains(timing.getId()))
            throw new ConcurrentMarkInsertion("Timing '" + timing.getId() + "' have been updating by someone");

        timings.add(timing.getId());
    }

    @AfterReturning(value = "execution (* ru.variousvar.timebalancer.service.MarkService.createTimeMark*(ru.variousvar.timebalancer.entity.Timing, ..)) && args(timing, ..))")
    public void unlockTiming(Timing timing) {
        timings.remove(timing.getId());
    }

    @AfterThrowing(value = "execution (* ru.variousvar.timebalancer.service.MarkService.createTimeMark*(ru.variousvar.timebalancer.entity.Timing, ..)) && args(timing, ..))",
            throwing = "ex")
    public void unlockTimingAfterSomeException(Timing timing, Exception ex) {
        if (!(ex instanceof ConcurrentMarkInsertion)) {
            timings.remove(timing.getId());
        }
    }
}
