package pl.studentmed.facultative.services.user_info;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoCreateDTO;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
class UserInfoController {

    private final UserInfoFacade facade;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfo createUser(@Valid @RequestBody UserInfoCreateDTO dto) {
        return facade.createUser(dto);
    }

}
