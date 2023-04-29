package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.addresses.Address;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoCreateDTO;

@Component
@RequiredArgsConstructor
class UserInfoCreator {

    private final UserInfoRepository repository;

    public UserInfo createUserInfo(UserInfoCreateDTO dto, Address userAddress) {
        var userInfo = buildUserInfoEntity(dto, userAddress);
        return repository.saveAndFlush(userInfo);
    }

    private UserInfo buildUserInfoEntity(UserInfoCreateDTO dto, Address userAddress) {
        return UserInfo.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(dto.password())
                .phoneNumber(dto.phoneNumber())
                .birthdate(dto.birthdate())
                .pesel(dto.pesel())
                .role(dto.role())
                .address(userAddress)
                .build();
    }

}
