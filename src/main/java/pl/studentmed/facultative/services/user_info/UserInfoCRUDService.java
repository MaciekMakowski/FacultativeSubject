package pl.studentmed.facultative.services.user_info;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.addresses.Address;
import pl.studentmed.facultative.models.user_info.Role;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoCreateDTO;

@Service
@RequiredArgsConstructor
public class UserInfoCRUDService {

    private final UserInfoReader reader;
    private final UserInfoCreator creator;


    public UserInfo getUserInfoById(Long userInfoId) {
        return reader.getUserInfoById(userInfoId);
    }

    public UserInfo createUser(UserInfoCreateDTO dto, Address patientAddress, Role role) {
        return creator.createUserInfo(dto, patientAddress, role);
    }

    public boolean existsByEmailOrPesel(String email, String pesel) {
        return reader.existsByEmailOrPesel(email, pesel);
    }
}
