package pl.studentmed.facultative.models.user_info;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserInfoLoginResponseDTO(Long userInfoId,
                                       String sessionId,
                                       String email,
                                       String firstName,
                                       String lastName,
                                       String pesel,
                                       String role,
                                       Long patientId,
                                       Long addressId,
                                       Long doctorId) {

    @Builder public UserInfoLoginResponseDTO {}

}

