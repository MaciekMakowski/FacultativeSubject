package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.address.Address;
import pl.studentmed.facultative.models.user_info.Role;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoCreateDTO;

@Component
@RequiredArgsConstructor
class UserInfoCreator {

    private final UserInfoRepository repository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserInfo createUserInfo(UserInfoCreateDTO dto, Address userAddress, Role role) {
        var userInfo = buildUserInfoEntityWithEncodedPassword(dto, userAddress, role);
        return repository.saveAndFlush(userInfo);
    }

    private UserInfo buildUserInfoEntityWithEncodedPassword(UserInfoCreateDTO dto, Address userAddress, Role role) {
        return UserInfo.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .phoneNumber(dto.phoneNumber())
                .birthdate(dto.birthdate())
                .pesel(dto.pesel())
                .role(role)
                .address(userAddress)
                .build();
    }

}
