package pl.studentmed.facultative.models.patient;

import pl.studentmed.facultative.models.user_info.IUserInfoDTO;
import pl.studentmed.facultative.models.user_info.UserInfoResponseDTO;

public record PatientResponseDTO(Long patientId, UserInfoResponseDTO patientUserInfo) implements IUserInfoDTO {
}
