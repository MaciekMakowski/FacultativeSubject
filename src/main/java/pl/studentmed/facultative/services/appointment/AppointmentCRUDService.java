package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.exceptions.AppointmentDateAlreadyTakenException;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.patient.Patient;

@Service
@RequiredArgsConstructor
public class AppointmentCRUDService {

    private final AppointmentCreator creator;
    private final AppointmentReader reader;

    public Appointment getAppointmentById(Long appointmentId) {
        return reader.getAppointmentById(appointmentId);
    }

    public Appointment createAppointment(Patient patient, Doctor doctor, AppointmentDate appointmentDate, String patientSymptoms) {
        if (reader.checkIfDoctorHasAppointementOnThisDate(doctor, appointmentDate)) {
            throw new AppointmentDateAlreadyTakenException("appointment", "This date is already taken.");
        }
        return creator.createAppointment(patient, doctor, appointmentDate, patientSymptoms);
    }

}
