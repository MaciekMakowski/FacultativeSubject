package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.user_info.UserInfo;

@Component
@RequiredArgsConstructor
class UserInfoReader {

    private final UserInfoRepository repository;

    public UserInfo getUserInfoById(Long userInfoId) {
        return repository.findById(userInfoId)
                .orElseThrow(
                        () -> new EntityNotFoundException("userInfo", "User info with id " + userInfoId + " doesn't exists.")
                );
    }

    public boolean existsByEmailOrPesel(String email, String pesel) {
        return repository.existsByEmailOrPesel(email, pesel);
    }
}
