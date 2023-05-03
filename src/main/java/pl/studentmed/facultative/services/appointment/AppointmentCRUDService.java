package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.exceptions.AppointmentDateAlreadyTakenException;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentBusyHoursDTO;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.patient.Patient;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentCRUDService {

    private final AppointmentCreator creator;
    private final AppointmentReader reader;

    public AppointmentResponseDTO getAppointmentById(Long appointmentId) {
        return reader.getAppointmentById(appointmentId);
    }

    public Appointment createAppointment(Patient patient, Doctor doctor, AppointmentDate appointmentDate, String patientSymptoms) {
        if (reader.checkIfDoctorHasAppointementOnThisDate(doctor, appointmentDate)) {
            throw new AppointmentDateAlreadyTakenException("appointment", "This date is already taken.");
        }
        return creator.createAppointment(patient, doctor, appointmentDate, patientSymptoms);
    }

    public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId, LocalDate date, Integer offset, Integer limit) {
        return reader.getDoctorAppointments(doctorId, date, offset, limit);
    }

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, LocalDate date, Integer offset, Integer limit) {
        return reader.getPatientAppointments(patientId, date, offset, limit);
    }

    public List<AppointmentBusyHoursDTO> getBusyAppointmentHoursForDate(LocalDate givenDate) {
        return reader.getBusyAppointmentHoursForDate(givenDate);
    }

}
