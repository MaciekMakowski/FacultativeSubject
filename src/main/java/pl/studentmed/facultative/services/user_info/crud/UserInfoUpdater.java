package pl.studentmed.facultative.services.user_info.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.user_info.UserInfo;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class UserInfoUpdater {

    private final UserInfoRepository repository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserInfo updateUserInfo(UserInfo userInfo,
                                   String firstName,
                                   String lastName,
                                   String password,
                                   String phoneNumber,
                                   LocalDate birthdate,
                                   String email) {
        if (firstName != null) {
            userInfo.setFirstName(firstName);
        }
        if (lastName != null) {
            userInfo.setLastName(lastName);
        }
        if (password != null) {
            userInfo.setPassword(passwordEncoder.encode(password));
        }
        if (phoneNumber != null) {
            userInfo.setPhoneNumber(phoneNumber);
        }
        if (birthdate != null) {
            userInfo.setBirthdate(birthdate);
        }
        if (email != null) {
            userInfo.setEmail(email);
        }
        return repository.saveAndFlush(userInfo);
    }

}
