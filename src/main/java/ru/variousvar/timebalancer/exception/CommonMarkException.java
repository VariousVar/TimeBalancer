package ru.variousvar.timebalancer.exception;

import ru.variousvar.timebalancer.service.MarkCreationStatus;

public class CommonMarkException extends RuntimeException {
    private String message;
    private MarkCreationStatus status;

    public CommonMarkException(String message, MarkCreationStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MarkCreationStatus getStatus() {
        return status;
    }
}
