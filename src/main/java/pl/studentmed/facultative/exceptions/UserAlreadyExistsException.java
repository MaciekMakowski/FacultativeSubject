package pl.studentmed.facultative.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public final String fieldName;

    public UserAlreadyExistsException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
