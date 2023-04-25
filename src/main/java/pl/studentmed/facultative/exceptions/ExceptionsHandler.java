package pl.studentmed.facultative.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = EmptyFieldException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage emptyFieldException(EmptyFieldException exception) {
        return new ErrorMessage(exception.fieldName, exception.getMessage());
    }

    @ExceptionHandler(value = InvalidLengthException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidLengthException(InvalidLengthException exception) {
        return new ErrorMessage(exception.fieldName, exception.getMessage());
    }

    @ExceptionHandler(value = InvalidZipCodeException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidLengthException(InvalidZipCodeException exception) {
        return new ErrorMessage("zipCode", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidSignsException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidSignsException(InvalidSignsException exception) {
        return new ErrorMessage(exception.fieldName, exception.getMessage());
    }

    @ExceptionHandler(value = InvalidAppointmentStatusException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidAppointmentStatusException(InvalidAppointmentStatusException exception) {
        return new ErrorMessage("appointmentStatus", exception.getMessage());
    }

}
