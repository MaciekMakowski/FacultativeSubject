package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.UserAlreadyExistsException;
import pl.studentmed.facultative.models.user_info.*;
import pl.studentmed.facultative.services.addresses.crud.AddressCRUDService;
import pl.studentmed.facultative.services.doctor.crud.DoctorCRUDService;
import pl.studentmed.facultative.services.patient.crud.PatientCRUDService;
import pl.studentmed.facultative.services.user_info.crud.UserInfoCRUDService;
import pl.studentmed.facultative.services.user_security.SessionRegistry;

import static pl.studentmed.facultative.models.DTOMapper.toDTO;

@Component
@RequiredArgsConstructor
class UserInfoFacade {

    private final UserInfoCRUDService userInfoCRUDService;
    private final AddressCRUDService addressCRUDService;
    private final PatientCRUDService patientCRUDService;
    private final DoctorCRUDService doctorCRUDService;
    private final AuthenticationManager authenticationManager;
    private final SessionRegistry sessionRegistry;

    public UserInfoResponseDTO createUser(UserInfoCreateDTO user) {
        if (userInfoCRUDService.existsByEmailOrPesel(user.email(), user.pesel())) {
            throw new UserAlreadyExistsException("registration", "User with this email or pesel already exists.");
        }
        if (Role.isReception(user.role())) {
            var userInfo = userInfoCRUDService.createUser(user, null, Role.RECEPTION);
            return toDTO(userInfo);
        }
        else if (Role.isDoctor(user.role())) {
            var doctorAddress = addressCRUDService.createEmptyAddress();
            var userInfo = userInfoCRUDService.createUser(user, doctorAddress, Role.DOCTOR);
            var doctor = doctorCRUDService.createDoctor(userInfo);
            return toDTO(doctor.getUserInfo());
        }
        else {
            var patientAddress = addressCRUDService.createEmptyAddress();
            var userInfo = userInfoCRUDService.createUser(user, patientAddress, Role.PATIENT);
            var patient = patientCRUDService.createPatient(userInfo);
            return toDTO(patient.getUserInfo());
        }
    }

    public UserInfoLoginResponseDTO loginUser(UserInfoLoginRequestDTO userInfoLoginRequestDTO) {
        userInfoCRUDService.existsByEmail(userInfoLoginRequestDTO.email());
        userInfoCRUDService.checkCredentials(userInfoLoginRequestDTO.email(), userInfoLoginRequestDTO.password());

        var userToLoginInto = userInfoCRUDService.getRegisteredUserInfoByEmail(userInfoLoginRequestDTO.email());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userInfoLoginRequestDTO.email(),
                userInfoLoginRequestDTO.password())
        );

        final String sessionId = sessionRegistry.registerSession(userInfoLoginRequestDTO.email());

        return UserInfoLoginResponseDTO.builder()
                .userInfoId(userToLoginInto.getId())
                .sessionId(sessionId)
                .email(userToLoginInto.getEmail())
                .firstName(userToLoginInto.getFirstName())
                .lastName(userToLoginInto.getLastName())
                .pesel(userToLoginInto.getPesel())
                .role(userToLoginInto.getRole().value)
                .addressId(userToLoginInto.getAddress().getId())
                .patientId(patientCRUDService.getPatientByUserInfoId(userToLoginInto.getId()) != null ? patientCRUDService.getPatientByUserInfoId(userToLoginInto.getId()).getId() : null)
                .doctorId(doctorCRUDService.getDoctorByUserInfoId(userToLoginInto.getId()) != null ? doctorCRUDService.getDoctorByUserInfoId(userToLoginInto.getId()).getId() : null)
                .build();
    }

}
