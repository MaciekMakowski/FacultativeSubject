package pl.studentmed.facultative.models.user_info;

import javax.validation.constraints.*;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

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

