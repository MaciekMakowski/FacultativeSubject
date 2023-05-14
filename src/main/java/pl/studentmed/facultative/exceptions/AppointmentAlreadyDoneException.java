package pl.studentmed.facultative.exceptions;

public class AppointmentAlreadyDoneException extends RuntimeException{

    public final String fieldName;

    public AppointmentAlreadyDoneException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

}
