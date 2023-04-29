package pl.studentmed.facultative.services.user_info;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.user_info.UserInfo;

interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    boolean existsByEmailOrPesel(String email, String pesel);

}
