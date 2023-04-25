package pl.studentmed.facultative.services.user_info;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.user_info.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {



}
