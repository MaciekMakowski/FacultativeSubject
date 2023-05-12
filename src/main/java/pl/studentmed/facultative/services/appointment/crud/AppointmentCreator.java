package pl.studentmed.facultative.services.appointment.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.patient.Patient;

@Component
@RequiredArgsConstructor
class AppointmentCreator {

    private final AppointmentRepository repository;

    public Appointment createAppointment(Patient patient, Doctor doctor, AppointmentDate appointmentDate, String patientSymptoms, String medicinesTaken) {
        Appointment appointment = new Appointment(patient, doctor, appointmentDate, patientSymptoms, medicinesTaken);
        return repository.saveAndFlush(appointment);
    }

}
