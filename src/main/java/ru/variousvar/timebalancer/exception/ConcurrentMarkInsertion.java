package ru.variousvar.timebalancer.exception;

public class ConcurrentMarkInsertion extends RuntimeException {
    private String message;

    public ConcurrentMarkInsertion(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
