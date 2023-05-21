package pl.studentmed.facultative.services.user_info.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.user_info.UserInfo;

@Component
@RequiredArgsConstructor
class UserInfoUpdater {

    private final UserInfoRepository repository;

    public UserInfo updateUserInfo(UserInfo userInfo,
                                   String phoneNumber,
                                   String email) {
        if (phoneNumber != null) {
            userInfo.setPhoneNumber(phoneNumber);
        }
        if (email != null) {
            userInfo.setEmail(email);
        }
        return repository.saveAndFlush(userInfo);
    }

}
