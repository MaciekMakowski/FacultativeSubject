package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.appointment.Appointment;

@Component
@RequiredArgsConstructor
class AppointmentReader {

    private final AppointmentRepository repository;

    public Appointment getAppointmentById(Long appointmentId) {
        return repository.findById(appointmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("appointment", "Appointment with id: " + appointmentId + " doesn't exists.")
                );
    }

}
