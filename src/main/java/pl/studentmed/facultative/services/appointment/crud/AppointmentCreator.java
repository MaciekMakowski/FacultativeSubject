package pl.studentmed.facultative.services.appointment.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.appointment.AppointmentStatus;
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

    public Appointment createControlAppointment(Appointment appointment, AppointmentDate appointmentControlDate) {
        var patientSymptoms = "Control appointment patient's with symptoms:\n" + appointment.getPatientSymptoms();
        Appointment controlAppointment = new Appointment(appointment.getPatient(), appointment.getDoctor(), appointmentControlDate, patientSymptoms, appointment.getMedicinesTaken());

        var statusApproved = AppointmentStatus.getAppointmentStatus("approved");
        controlAppointment.setStatus(statusApproved);

        return repository.saveAndFlush(controlAppointment);
    }

}
