package pl.studentmed.facultative.models.appointment;

import pl.studentmed.facultative.exceptions.InvalidAppointmentStatusException;

import java.util.stream.Stream;

public enum AppointmentStatus {

    NEW("new"),
    APPROVED("approved"),
    CANCELED("canceled"),
    DONE("done"),
    RESCHEDULED("rescheduled");

    public final String value;

    AppointmentStatus(String value) {
        this.value = value;
    }

    public static AppointmentStatus getAppointmentStatus(String givenValue) {
        return Stream.of(values())
                .filter(appointmentStatus -> appointmentStatus.value.equals(givenValue))
                .findFirst()
                .orElseThrow(() -> new InvalidAppointmentStatusException(String.format("Appointment status must be one of '%s', '%s', '%s', '%s', or '%s'",
                        NEW.value, APPROVED.value, CANCELED.value, DONE.value, RESCHEDULED.value)));
    }

    public static boolean isCanceled(AppointmentStatus appointmentStatus) {
        return appointmentStatus.equals(AppointmentStatus.CANCELED);
    }

}
