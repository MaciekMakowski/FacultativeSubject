package pl.studentmed.facultative.services.user_info.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.user_info.UserInfo;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class UserInfoUpdater {

    private final UserInfoRepository repository;

    public UserInfo updateUserInfo(UserInfo userInfo,
                                   String firstName,
                                   String lastName,
                                   String phoneNumber,
                                   LocalDate birthdate) {
        if (firstName != null) {
            userInfo.setFirstName(firstName);
        }
        if (lastName != null) {
            userInfo.setLastName(lastName);
        }
        if (phoneNumber != null) {
            userInfo.setPhoneNumber(phoneNumber);
        }
        if (birthdate != null) {
            userInfo.setBirthdate(birthdate);
        }
        return repository.saveAndFlush(userInfo);
    }

}
