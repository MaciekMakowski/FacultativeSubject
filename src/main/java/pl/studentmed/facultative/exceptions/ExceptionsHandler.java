package pl.studentmed.facultative.exceptions;

import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static pl.studentmed.facultative.exceptions.ErrorMessage.extractFieldName;

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
    public List<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        return result.getFieldErrors().stream()
                .map(fieldError -> new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErrorMessage> constraintViolationException(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(violation -> new ErrorMessage(extractFieldName(violation), violation.getMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage userAlreadyExistsException(UserAlreadyExistsException exception) {
        return new ErrorMessage(exception.fieldName, exception.getMessage());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage badCredentialsException() {
        return new ErrorMessage("password", "Wrong password or email entered.");
    }

    @ExceptionHandler(value = AppointmentCantBeCancelledException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage appointmentCantBeCancelledException(AppointmentCantBeCancelledException exception) {
        return new ErrorMessage(exception.fieldName, exception.getMessage());
    }

}
