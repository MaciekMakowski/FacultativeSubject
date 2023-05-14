package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO;
import pl.studentmed.facultative.models.doctor.DoctorUserInfoDTO;
import pl.studentmed.facultative.services.appointment.crud.AppointmentCRUDService;
import pl.studentmed.facultative.services.doctor.crud.DoctorCRUDService;

import java.time.LocalDate;
import java.util.List;

import static pl.studentmed.facultative.models.DTOMapper.toDTO;

@Component
@RequiredArgsConstructor
class DoctorFacade {

    private final DoctorCRUDService doctorCRUDService;
    private final AppointmentCRUDService appointmentCRUDService;

    public DoctorUserInfoDTO getDoctorById(Long doctorId) {
        var doctor = doctorCRUDService.getDoctorById(doctorId);
        return toDTO(doctor, doctor.getUserInfo(), doctor.getUserInfo().getAddress());
    }

    public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId, LocalDate date, Integer offset, Integer limit) {
        return appointmentCRUDService.getDoctorAppointments(doctorId, date, offset, limit);
    }

    public List<DoctorSpecializationDTO> getAllDoctors() {
        return doctorCRUDService.getAllDoctors();
    }

    public List<DoctorSpecializationDTO> getDoctorsBySpecialization(String specialization) {
        return doctorCRUDService.getDoctorsBySpecialization(specialization);
    }

    public DoctorSpecializationDTO changeDoctorSpecialization(Long doctorId, String specialization) {
        return doctorCRUDService.changeDoctorSpecialization(doctorId, specialization);
    }

}
