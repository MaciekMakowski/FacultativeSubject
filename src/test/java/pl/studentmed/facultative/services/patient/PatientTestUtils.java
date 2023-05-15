package pl.studentmed.facultative.services.patient;

import pl.studentmed.facultative.models.user_info.Role;
import pl.studentmed.facultative.models.user_info.UserInfo;

import java.time.LocalDate;

public class PatientTestUtils {

    public static UserInfo createUserInfo(Long userInfoId) {
        UserInfo userInfo = UserInfo.builder()
                .firstName("Test")
                .lastName("Test")
                .pesel("12312312300")
                .birthdate(LocalDate.of(2001, 11, 11))
                .password("sometestpass")
                .email("test@email.pl")
                .role(Role.PATIENT)
                .build();
        userInfo.setId(userInfoId);
        return userInfo;
    }

}
