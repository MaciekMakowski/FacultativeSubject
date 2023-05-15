package pl.studentmed.facultative.models.doctor;

import pl.studentmed.facultative.models.address.AddressResponseDTO;
import pl.studentmed.facultative.models.user_info.UserInfoResponseDTO;

public record DoctorResponseDTO(Long doctorId,
                                String specialization,
                                String description,
                                String photo,
                                UserInfoResponseDTO userInfoResponseDTO,
                                AddressResponseDTO addressResponseDTO) {
}
