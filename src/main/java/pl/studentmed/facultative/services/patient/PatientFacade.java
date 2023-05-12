package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.appointment.AppointmentStatus;
import pl.studentmed.facultative.models.patient.PatientResponseDTO;
import pl.studentmed.facultative.models.patient.PatientUpdateDTO;
import pl.studentmed.facultative.services.addresses.crud.AddressCRUDService;
import pl.studentmed.facultative.services.appointment.crud.AppointmentCRUDService;
import pl.studentmed.facultative.services.patient.crud.PatientCRUDService;
import pl.studentmed.facultative.services.user_info.crud.UserInfoCRUDService;

import java.time.LocalDate;
import java.util.List;

import static pl.studentmed.facultative.models.DTOMapper.toDTO;

@Component
@RequiredArgsConstructor
class PatientFacade {

    private final PatientCRUDService patientCRUDService;
    private final AppointmentCRUDService appointmentCRUDService;
    private final AddressCRUDService addressCRUDService;
    private final UserInfoCRUDService userInfoCRUDService;

    public PatientResponseDTO getPatientById(Long patientId) {
        var patient = patientCRUDService.getPatientById(patientId);
        return toDTO(patient, patient.getUserInfo(), patient.getUserInfo().getAddress());
    }

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, String status, LocalDate appointmentDate, LocalDate secondDate, Integer offset, Integer limit) {
        var appointmentStatus = AppointmentStatus.getAppointmentStatus(status);
        return appointmentCRUDService.getPatientAppointments(patientId, appointmentStatus, appointmentDate, secondDate, offset, limit);
    }

    public PatientResponseDTO updatePatient(PatientUpdateDTO patientUpdateDTO) {
        var userInfo = userInfoCRUDService.updateUserInfo(patientUpdateDTO.userInfoUpdateDTO());
        var address = addressCRUDService.updateAddress(patientUpdateDTO.addressUpdateDTO());
        var patient = patientCRUDService.updatePatient(patientUpdateDTO);
        return toDTO(patient, userInfo, address);
    }

}
