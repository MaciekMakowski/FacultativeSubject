package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.*;
import pl.studentmed.facultative.services.appointment.crud.AppointmentCRUDService;
import pl.studentmed.facultative.services.doctor.crud.DoctorCRUDService;
import pl.studentmed.facultative.services.patient.crud.PatientCRUDService;
import pl.studentmed.facultative.services.user_info.crud.UserInfoCRUDService;

import java.time.LocalDate;
import java.util.List;

import static pl.studentmed.facultative.models.DTOMapper.toDTO;

@Component
@RequiredArgsConstructor
class AppointmentFacade {

    private final AppointmentCRUDService appointmentCRUDService;
    private final PatientCRUDService patientCRUDService;
    private final DoctorCRUDService doctorCRUDService;
    private final AppointmentStatsCalculator appointmentStatsCalculator;
    private final AppointmentBusyHoursService appointmentBusyHoursService;
    private final UserInfoCRUDService userInfoCRUDService;

    public AppointmentResponseDTO getAppointmentDTOById(Long appointmentId) {
        return appointmentCRUDService.getAppointmentDTOById(appointmentId);
    }

    public List<AppointmentResponseDTO> getAllNewAppointments(Integer offset, Integer limit) {
        return appointmentCRUDService.getAllNewAppointments(offset, limit);
    }

    public List<AppointmentsHoursDTO> getBusyAppointmentHoursForDateAndDoctorId(LocalDate givenDate, Long doctorId) {
        var busyHours = appointmentCRUDService.getBusyAppointmentHoursForDateAndDoctorId(givenDate, doctorId);
        return appointmentBusyHoursService.getAppointmentHours(busyHours);
    }

    public AppointmentResponseDTO createAppointment(AppointmentCreateDTO dto) {
        var patient = patientCRUDService.getPatientById(dto.patientId());
        var doctor = doctorCRUDService.getDoctorById(dto.doctorId());
        var appointmentDate = new AppointmentDate(dto.appointmentDate());
        var appointment = appointmentCRUDService.createAppointment(patient, doctor, appointmentDate, dto.patientSymptoms(), dto.medicinesTaken());
        return toDTO(appointment);
    }

    public AppointmentResponseDTO editAppointment(AppointmentEditDTO dto) {
        var appointmentToEdit = appointmentCRUDService.getAppointmentById(dto.appointmentId());
        var userWhichIsEditing = userInfoCRUDService.getUserInfoById(dto.userInfoId());
        var editedAppointment = appointmentCRUDService.editAppointment(appointmentToEdit, dto, userWhichIsEditing);
        return toDTO(editedAppointment);
    }

    public AppointmentResponseDTO finnishAppointment(Long appointmentId, String recommendations) {
        var appointment = appointmentCRUDService.finnishAppointment(appointmentId, recommendations);
        return toDTO(appointment);
    }

    public AllAppointmentsStatsDTO getClinicAndDoctorsStats() {
        var appointments = appointmentCRUDService.getAllAppointments();
        var doctors = doctorCRUDService.getAllDoctorsEntities();
        return appointmentStatsCalculator.countClinicAndDoctorsStats(appointments, doctors);
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentCRUDService.getAllAppointmentsMapped();
    }
}
