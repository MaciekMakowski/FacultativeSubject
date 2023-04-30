package pl.studentmed.facultative.models;

import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.DoctorResponseDTO;
import pl.studentmed.facultative.models.patient.Patient;
import pl.studentmed.facultative.models.patient.PatientResponseDTO;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoResponseDTO;

import static pl.studentmed.facultative.models.StudentMedDateUtils.DAY_MONTH_YEAR;
import static pl.studentmed.facultative.models.StudentMedDateUtils.DAY_MONTH_YEAR_TIME;

@Component
public class DTOMapper {

    public PatientResponseDTO toPatientResponseDTO(Patient patient, UserInfo userInfo) {
            return new PatientResponseDTO(patient.getId(), toUserInfoResponseDTO(userInfo));
    }

    public DoctorResponseDTO toDoctorResponseDTO(Doctor doctor, UserInfo userInfo) {
        return new DoctorResponseDTO(doctor.getId(), doctor.getSpecialization(), toUserInfoResponseDTO(userInfo));
    }

    public UserInfoResponseDTO toUserInfoResponseDTO(UserInfo userInfo) {
        return UserInfoResponseDTO.builder()
                .userInfoId(userInfo.getId())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .email(userInfo.getEmail())
                .userRole(userInfo.getRole().value)
                .birthdate(userInfo.getBirthdate().format(DAY_MONTH_YEAR))
                .createdAt(userInfo.getCreatedAt().format(DAY_MONTH_YEAR_TIME))
                .modifiedAt(userInfo.getModifiedAt().format(DAY_MONTH_YEAR_TIME))
                .build();
    }

}
