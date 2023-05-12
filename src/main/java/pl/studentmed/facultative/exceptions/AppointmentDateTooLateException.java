package pl.studentmed.facultative.exceptions;

public class AppointmentDateTooLateException extends RuntimeException{

    public final String fieldName;

    public AppointmentDateTooLateException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

}
