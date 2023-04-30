package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.DTOMapper;
import pl.studentmed.facultative.models.appointment.AppointmentCreateDTO;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.services.doctor.DoctorCRUDService;
import pl.studentmed.facultative.services.patient.PatientCRUDService;

@Component
@RequiredArgsConstructor
public class AppointmentFacade {

    private final AppointmentCRUDService appointmentCRUDService;
    private final PatientCRUDService patientCRUDService;
    private final DoctorCRUDService doctorCRUDService;
    private final DTOMapper mapper;

    public AppointmentResponseDTO getAppointmentById(Long appointmentId) {
        return appointmentCRUDService.getAppointmentById(appointmentId);
    }

    public AppointmentResponseDTO createAppointment(AppointmentCreateDTO dto) {
        var patient = patientCRUDService.getPatientById(dto.patientId());
        var doctor = doctorCRUDService.getDoctorById(dto.doctorId());
        var appointmentDate = new AppointmentDate(dto.appointmentDate());
        var appointment = appointmentCRUDService.createAppointment(patient, doctor, appointmentDate, dto.patientSymptoms());
        return mapper.toAppointmentResponseDTO(appointment);
    }

}
