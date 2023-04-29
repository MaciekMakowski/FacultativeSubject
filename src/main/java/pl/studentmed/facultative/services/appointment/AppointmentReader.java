package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.doctor.Doctor;

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

    public boolean checkIfDoctorHasAppointementOnThisDate(Doctor doctor, AppointmentDate appointmentDate) {
        return repository.existsByAppointmentDateAndDoctor(appointmentDate, doctor);
    }

}
