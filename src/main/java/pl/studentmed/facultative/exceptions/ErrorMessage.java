package pl.studentmed.facultative.exceptions;

import javax.validation.ConstraintViolation;

public record ErrorMessage(String fieldName, String error) {

    static String extractFieldName(ConstraintViolation<?> violation) {
        var entireMessage = violation.getMessage();
        var dotIndex = entireMessage.indexOf(".");
        var colonIndex = entireMessage.indexOf(":");
        return entireMessage.substring(dotIndex + 1, colonIndex);
    }

}
