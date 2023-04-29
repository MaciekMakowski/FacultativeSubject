package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.addresses.Address;
import pl.studentmed.facultative.models.user_info.Role;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoCreateDTO;

@Component
@RequiredArgsConstructor
class UserInfoCreator {

    private final UserInfoRepository repository;

    public UserInfo createUserInfo(UserInfoCreateDTO dto, Address userAddress, Role role) {
        var userInfo = buildUserInfoEntity(dto, userAddress, role);
        return repository.saveAndFlush(userInfo);
    }

    private UserInfo buildUserInfoEntity(UserInfoCreateDTO dto, Address userAddress, Role role) {
        return UserInfo.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(dto.password())
                .phoneNumber(dto.phoneNumber())
                .birthdate(dto.birthdate())
                .pesel(dto.pesel())
                .role(role)
                .address(userAddress)
                .build();
    }

}
