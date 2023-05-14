package pl.studentmed.facultative.exceptions;

public class InvalidAppointmentStatusException extends RuntimeException{

    public InvalidAppointmentStatusException(String message) {
        super(message);
    }

}
