package pl.studentmed.facultative.exceptions;

public class AppointmentCantBeCancelledException extends RuntimeException {

    public final String fieldName;

    public AppointmentCantBeCancelledException() {
        super("Appointment cant be cancelled before 24 hours to it.");
        this.fieldName = "appointment";
    }
}
