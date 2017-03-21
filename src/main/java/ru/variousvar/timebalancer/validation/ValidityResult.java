package ru.variousvar.timebalancer.validation;

public class ValidityResult {
    private String field;
    private Object value;
    private boolean valid;
    private String message;

    public ValidityResult(String field, Object value, boolean valid, String message) {
        this.field = field;
        this.value = value;
        this.valid = valid;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
