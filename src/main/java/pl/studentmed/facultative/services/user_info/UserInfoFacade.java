package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.UserAlreadyExistsException;
import pl.studentmed.facultative.models.DTOMapper;
import pl.studentmed.facultative.models.user_info.*;
import pl.studentmed.facultative.services.addresses.AddressCRUDService;
import pl.studentmed.facultative.services.doctor.DoctorCRUDService;
import pl.studentmed.facultative.services.patient.PatientCRUDService;

@Component
@RequiredArgsConstructor
class UserInfoFacade {

    private final UserInfoCRUDService userInfoCRUDService;
    private final AddressCRUDService addressCRUDService;
    private final PatientCRUDService patientCRUDService;
    private final DoctorCRUDService doctorCRUDService;
    private final DTOMapper mapper;

    public IUserInfoDTO createUser(UserInfoCreateDTO user) {
        if (userInfoCRUDService.existsByEmailOrPesel(user.email(), user.pesel())) {
            throw new UserAlreadyExistsException("registration", "User with this email or pesel already exists.");
        }
        if (Role.isReception(user.role())) {
            var userInfo = userInfoCRUDService.createUser(user, null, Role.RECEPTION);
            return mapper.toUserInfoResponseDTO(userInfo);
        }
        else if (Role.isDoctor(user.role())) {
            var doctorAddress = addressCRUDService.createEmptyAddress();
            var userInfo = userInfoCRUDService.createUser(user, doctorAddress, Role.DOCTOR);
            var doctor = doctorCRUDService.createDoctor(userInfo);
            return mapper.toDoctorResponseDTO(doctor, userInfo);
        }
        else {
            var patientAddress = addressCRUDService.createEmptyAddress();
            var userInfo = userInfoCRUDService.createUser(user, patientAddress, Role.PATIENT);
            var patient = patientCRUDService.createPatient(userInfo);
            return mapper.toPatientResponseDTO(patient, userInfo);
        }
    }

    public UserInfoLoginResponseDTO loginUser(UserInfoLoginRequestDTO userInfoLoginRequestDTO) {
        return userInfoCRUDService.loginUser(userInfoLoginRequestDTO);
    }

}
