package pl.studentmed.facultative.models.user_info;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
public record UserInfoLoginRequestDTO(@NotNull
                               @Size(max = 30)
                               @Email
                               String email,
                                      @NotNull
                               @Size(min = 8, max = 25)
                               String password) {

    @Builder public UserInfoLoginRequestDTO {}

}

