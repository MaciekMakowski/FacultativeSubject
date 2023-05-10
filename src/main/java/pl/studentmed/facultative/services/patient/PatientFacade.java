package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.patient.PatientResponseDTO;
import pl.studentmed.facultative.services.appointment.crud.AppointmentCRUDService;
import pl.studentmed.facultative.services.patient.crud.PatientCRUDService;

import java.time.LocalDate;
import java.util.List;

import static pl.studentmed.facultative.models.DTOMapper.toDTO;

@Component
@RequiredArgsConstructor
class PatientFacade {

    private final PatientCRUDService patientCRUDService;
    private final AppointmentCRUDService appointmentCRUDService;

    public PatientResponseDTO getPatientById(Long patientId) {
        var patient = patientCRUDService.getPatientById(patientId);
        return toDTO(patient, patient.getUserInfo(), patient.getUserInfo().getAddress());
    }

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, LocalDate appointmentDate, LocalDate secondDate, Integer offset, Integer limit) {
        return appointmentCRUDService.getPatientAppointments(patientId, appointmentDate, secondDate, offset, limit);
    }

}
