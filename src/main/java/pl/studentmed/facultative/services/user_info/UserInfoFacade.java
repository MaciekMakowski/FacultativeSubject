package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoCreateDTO;
import pl.studentmed.facultative.services.addresses.AddressCRUDService;

@Component
@RequiredArgsConstructor
class UserInfoFacade {

    private final UserInfoCRUDService userInfoCRUDService;
    private final AddressCRUDService addressCRUDService;

    public UserInfo getUserInfoById(Long userInfoId) {
        return userInfoCRUDService.getUserInfoById(userInfoId);
    }

    public UserInfo createUserInfo(UserInfoCreateDTO dto) {
        var userAddress = addressCRUDService.createEmptyAddress();
        return userInfoCRUDService.createUser(dto, userAddress);
    }
}
