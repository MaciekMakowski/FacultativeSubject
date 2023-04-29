package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoCreateDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/userinfo")
@RequiredArgsConstructor
class UserInfoController {

    private final UserInfoFacade facade;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfo createPatient(@Valid @RequestBody UserInfoCreateDTO dto) {
        return facade.createUserInfo(dto);
    }

}
