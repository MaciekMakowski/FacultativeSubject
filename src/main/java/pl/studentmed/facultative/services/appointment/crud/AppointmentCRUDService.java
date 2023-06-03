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

    public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId, LocalDate date, Integer offset, Integer limit) {
        return reader.getDoctorAppointments(doctorId, date, offset, limit);
    }

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, AppointmentStatus status, LocalDate appointmentDate, LocalDate secondDate, Integer offset, Integer limit) {
        return reader.getPatientAppointments(patientId, status, appointmentDate, secondDate, offset, limit);
    }

    public List<AppointmentResponseDTO> getAllNewAppointments(Integer offset, Integer limit) {
        return reader.getAllNewAppointments(offset, limit);
    }

    public List<String> getBusyAppointmentHoursForDateAndDoctorId(LocalDate givenDate, Long doctorId) {
        return reader.getBusyAppointmentHoursForDateAndDoctorId(givenDate, doctorId);
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

    public Appointment editAppointment(Appointment appointment, AppointmentEditDTO dto) {
        return updater.editAppointment(appointment, dto);
    }

    public Appointment finnishAppointment(Long appointmentId, String recommendations) {
        var appointment = getAppointmentById(appointmentId);
        var appointmentControlDate = reader.getControlAppointmentDate(appointment);
        creator.createControlAppointment(appointment, appointmentControlDate);
        return updater.finnishAppointment(appointment, recommendations);
    }

    public List<Appointment> getAllAppointments() {
        return reader.getAllAppointmentEntities();
    }

    public List<AppointmentResponseDTO> getAllAppointmentsMapped() {
        return reader.getAllAppointmentsMapped();
    }
}
