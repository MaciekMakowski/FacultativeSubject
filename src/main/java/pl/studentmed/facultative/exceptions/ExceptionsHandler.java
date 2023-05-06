package pl.studentmed.facultative.exceptions;

import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(value = AppointmentDateAlreadyTakenException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage appointmentDateAlreadyTakenException(AppointmentDateAlreadyTakenException exception) {
        return new ErrorMessage(exception.fieldName, exception.getMessage());
    }

    @ExceptionHandler(value = InvalidRoleException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidRoleException(InvalidRoleException exception) {
        return new ErrorMessage("role", exception.getMessage());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage entityNotFoundException(EntityNotFoundException exception) {
        return new ErrorMessage(exception.entityName, exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ErrorMessage(exception.getFieldError().getField(), StringUtils.capitalize(exception.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage userAlreadyExsistsException(UserAlreadyExistsException exception) {
        return new ErrorMessage(exception.fieldName, exception.getMessage());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage badCredentialsException() {
        return new ErrorMessage("password", "You have written wrong email or password.");
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage constraintViolationException(ConstraintViolationException exception) {
        return ErrorMessage.of(exception);
    }

}
