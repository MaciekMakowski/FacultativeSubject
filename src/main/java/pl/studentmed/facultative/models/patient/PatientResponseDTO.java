package pl.studentmed.facultative.models.patient;

import pl.studentmed.facultative.models.address.AddressResponseDTO;
import pl.studentmed.facultative.models.user_info.IUserInfoDTO;
import pl.studentmed.facultative.models.user_info.UserInfoResponseDTO;

public record PatientResponseDTO(Long patientId,
                                 String allergies,
                                 String medicines,
                                 UserInfoResponseDTO patientUserInfo,
                                 AddressResponseDTO addressResponseDTO) implements IUserInfoDTO {
}
