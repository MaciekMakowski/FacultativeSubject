package pl.studentmed.facultative.exceptions;

public class AppointmentCantBeCancelledException extends RuntimeException {

    public final String fieldName;

    public AppointmentCantBeCancelledException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

}
