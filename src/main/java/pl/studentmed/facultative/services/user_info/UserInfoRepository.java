package pl.studentmed.facultative.services.user_info;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.user_info.UserInfo;
import java.util.Optional;

interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Boolean existsByEmailOrPesel(String email, String pesel);
    Boolean existsByEmail(String email);
    Optional<UserInfo> findByEmail(String email);
    UserInfo findRegisteredUserInfoByEmail(String email);

}
