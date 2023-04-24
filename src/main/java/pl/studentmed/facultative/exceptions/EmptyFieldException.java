package pl.studentmed.facultative.exceptions;

public class EmptyFieldException extends RuntimeException {

    public final String fieldName;

    public EmptyFieldException(String fieldName, String message) {
        super(fieldName + ' ' + message);
        this.fieldName = fieldName;
    }

}
