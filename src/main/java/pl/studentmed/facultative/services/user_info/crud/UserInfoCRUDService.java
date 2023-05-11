package pl.studentmed.facultative.services.user_info.crud;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.address.Address;
import pl.studentmed.facultative.models.user_info.*;

@Service
@RequiredArgsConstructor
public class UserInfoCRUDService {

    private final UserInfoReader reader;
    private final UserInfoCreator creator;
    private final UserInfoUpdater updater;


    public UserInfo getUserInfoById(Long userInfoId) {
        return reader.getUserInfoById(userInfoId);
    }

    public UserInfo getUserInfoByEmail(String userEmail) {
        return reader.getUserInfoByEmail(userEmail);
    }

    public UserInfo createUser(UserInfoCreateDTO dto, Address patientAddress, Role role) {
        return creator.createUserInfo(dto, patientAddress, role);
    }

    public UserInfoLoginResponseDTO loginUser(UserInfoLoginRequestDTO userInfoLoginRequestDTO) {
        return reader.loginUser(userInfoLoginRequestDTO);
    }

    public boolean existsByEmailOrPesel(String email, String pesel) {
        return reader.existsByEmailOrPesel(email, pesel);
    }

    public UserInfo updateUserInfo(UserInfoUpdateDTO userInfoUpdateDTO) {
        var userInfo = reader.getUserInfoById(userInfoUpdateDTO.userInfoId());
        return updater.updateUserInfo(
                userInfo,
                userInfoUpdateDTO.firstName(),
                userInfoUpdateDTO.lastName(),
                userInfoUpdateDTO.password(),
                userInfoUpdateDTO.phoneNumber(),
                userInfoUpdateDTO.birthdate()
        );
    }

}
