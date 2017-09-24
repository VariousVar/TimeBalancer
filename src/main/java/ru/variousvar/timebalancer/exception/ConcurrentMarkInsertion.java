package ru.variousvar.timebalancer.exception;

import ru.variousvar.timebalancer.service.MarkCreationStatus;

/**
 * Throws if someone trying to create a new mark concurrently with other on a same timing.
 */
public class ConcurrentMarkInsertion extends CommonMarkException {

    public ConcurrentMarkInsertion(String message) {
        this(message, MarkCreationStatus.UPDATING);
    }

    public ConcurrentMarkInsertion(String message, MarkCreationStatus status) {
        super(message, status);
    }

}
