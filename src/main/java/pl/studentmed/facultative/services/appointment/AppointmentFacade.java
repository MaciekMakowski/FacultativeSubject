package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.DTOMapper;
import pl.studentmed.facultative.models.appointment.*;
import pl.studentmed.facultative.services.doctor.DoctorCRUDService;
import pl.studentmed.facultative.services.patient.PatientCRUDService;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
class AppointmentFacade {

    private final AppointmentCRUDService appointmentCRUDService;
    private final PatientCRUDService patientCRUDService;
    private final DoctorCRUDService doctorCRUDService;
    private final DTOMapper mapper;

    public AppointmentResponseDTO getAppointmentDTOById(Long appointmentId) {
        return appointmentCRUDService.getAppointmentDTOById(appointmentId);
    }

    public AppointmentResponseDTO createAppointment(AppointmentCreateDTO dto) {
        var patient = patientCRUDService.getPatientById(dto.patientId());
        var doctor = doctorCRUDService.getDoctorById(dto.doctorId());
        var appointmentDate = new AppointmentDate(dto.appointmentDate());
        var appointment = appointmentCRUDService.createAppointment(patient, doctor, appointmentDate, dto.patientSymptoms());
        return mapper.toAppointmentResponseDTO(appointment);
    }

    public List<AppointmentBusyHoursDTO> getBusyAppointmentHoursForDate(LocalDate givenDate) {
        return appointmentCRUDService.getBusyAppointmentHoursForDate(givenDate);
    }

    public AppointmentResponseDTO editAppointment(AppointmentEditDTO dto) {
        var appointmentToEdit = appointmentCRUDService.getAppointmentById(dto.appointmentId());
        var editedAppointment = appointmentCRUDService.editAppointment(appointmentToEdit, dto);
        return mapper.toAppointmentResponseDTO(editedAppointment);
    }

}
