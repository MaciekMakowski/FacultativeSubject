package pl.studentmed.facultative.services.appointment.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.exceptions.AppointmentDateAlreadyTakenException;
import pl.studentmed.facultative.models.appointment.*;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.patient.Patient;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentCRUDService {

    private final AppointmentCreator creator;
    private final AppointmentReader reader;
    private final AppointmentUpdater updater;

    public AppointmentResponseDTO getAppointmentDTOById(Long appointmentId) {
        return reader.getAppointmentDTOById(appointmentId);
    }

    public Appointment getAppointmentById(Long appointmentId) {
        return reader.getAppointmentById(appointmentId);
    }

    public Appointment createAppointment(Patient patient, Doctor doctor, AppointmentDate appointmentDate, String patientSymptoms, String medicinesTaken) {
        isAppointmentDateAvailable(doctor, appointmentDate);
        return creator.createAppointment(patient, doctor, appointmentDate, patientSymptoms, medicinesTaken);
    }

    private void isAppointmentDateAvailable(Doctor doctor, AppointmentDate appointmentDate) {
        if (reader.checkIfDoctorHasAppointmentOnThisDate(doctor, appointmentDate)) {
            throw new AppointmentDateAlreadyTakenException("appointment", "This date is already taken.");
        }
    }

    public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId, LocalDate date, Integer offset, Integer limit) {
        return reader.getDoctorAppointments(doctorId, date, offset, limit);
    }

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, LocalDate appointmentDate, LocalDate secondDate, Integer offset, Integer limit) {
        return reader.getPatientAppointments(patientId, appointmentDate, secondDate, offset, limit);
    }

    public List<AppointmentBusyHoursDTO> getBusyAppointmentHoursForDateAndDoctorId(LocalDate givenDate, Long doctorId) {
        return reader.getBusyAppointmentHoursForDateAndDoctorId(givenDate, doctorId);
    }

    public Appointment editAppointment(Appointment appointment, AppointmentEditDTO dto) {
        if(dto.newAppointmentDate() != null) {
            var appointmentDate = new AppointmentDate(dto.newAppointmentDate());
            isAppointmentDateAvailable(appointment.getDoctor(), appointmentDate);
        }
        return updater.editAppointment(appointment, dto);
    }

}
