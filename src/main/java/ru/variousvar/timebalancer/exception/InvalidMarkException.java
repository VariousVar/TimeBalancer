package ru.variousvar.timebalancer.exception;

import ru.variousvar.timebalancer.service.MarkCreationStatus;
import ru.variousvar.timebalancer.validation.ValidityResult;

import java.util.ArrayList;
import java.util.List;

public class InvalidMarkException extends CommonMarkException {
    private List<ValidityResult> validation = new ArrayList<>();

    public InvalidMarkException(String message, MarkCreationStatus status) {
        super(message, status);
    }

    public InvalidMarkException addValidationResult(ValidityResult result) {
        validation.add(result);
        return this;
    }

    public List<ValidityResult> getValidationResults() {
        return validation;
    }
}
