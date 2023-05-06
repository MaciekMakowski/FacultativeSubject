package pl.studentmed.facultative.exceptions;

import javax.validation.ConstraintViolationException;

public record ErrorMessage(String fieldName, String error) {

    public static ErrorMessage of(ConstraintViolationException exception) {
        var fieldName = extractFieldName(exception);
        var error = extractMessage(exception);
        return new ErrorMessage(fieldName, error);
    }

    // example ConstraintViolationException message looks like this:
    // "getDoctorAppointments.offset: must be greater than or equal to 0"
    // so I'm extracting chars from '.' to ':' to get field name
    private static String extractFieldName(ConstraintViolationException exception) {
        var entireMessage = exception.getMessage();
        var dotIndex = entireMessage.indexOf(".");
        var colonIndex = entireMessage.indexOf(":");
        return entireMessage.substring(dotIndex + 1, colonIndex);
    }

    private static String extractMessage(ConstraintViolationException exception) {
        var entireMessage = exception.getMessage();
        var colonIndex = entireMessage.indexOf(":");
        return entireMessage.substring(colonIndex + 1);
    }

}
