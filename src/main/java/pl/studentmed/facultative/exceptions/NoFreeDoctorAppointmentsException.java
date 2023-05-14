package pl.studentmed.facultative.exceptions;

public class NoFreeDoctorAppointmentsException extends RuntimeException{

    public final String fieldName;

    public NoFreeDoctorAppointmentsException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

}
