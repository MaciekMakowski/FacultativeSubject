package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.patient.Patient;
import pl.studentmed.facultative.services.appointment.AppointmentCRUDService;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
class PatientFacade {

    private final PatientCRUDService patientCRUDService;
    private final AppointmentCRUDService appointmentCRUDService;

    public Patient getPatientById(Long patientId) {
        return patientCRUDService.getPatientById(patientId);
    }

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, LocalDate date, Integer offset, Integer limit) {
        return appointmentCRUDService.getPatientAppointments(patientId, date, offset, limit);
    }

}
