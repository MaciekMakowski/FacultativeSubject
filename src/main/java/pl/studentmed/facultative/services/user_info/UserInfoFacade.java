package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.InvalidRoleException;
import pl.studentmed.facultative.exceptions.UserAlreadyExistsException;
import pl.studentmed.facultative.models.user_info.Role;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoCreateDTO;
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

    public UserInfo createUser(UserInfoCreateDTO user) {
        if (userInfoCRUDService.existsByEmailOrPesel(user.email(), user.pesel())) {
            throw new UserAlreadyExistsException("registration", "User with this email or pesel already exists.");
        }
        if (Role.isPatient(user.role())) {
            var patientAddress = addressCRUDService.createEmptyAddress();
            var userInfo = userInfoCRUDService.createUser(user, patientAddress, Role.PATIENT);
            patientCRUDService.createPatient(userInfo);
            return userInfo;
        }
        if (Role.isDoctor(user.role())) {
            var doctorAddress = addressCRUDService.createEmptyAddress();
            var userInfo = userInfoCRUDService.createUser(user, doctorAddress, Role.DOCTOR);
            doctorCRUDService.createDoctor(userInfo);
            return userInfo;
        }
        if (Role.isReception(user.role())) {
            return userInfoCRUDService.createUser(user, null, Role.RECEPTION);
        }
        throw new InvalidRoleException();
    }

}
