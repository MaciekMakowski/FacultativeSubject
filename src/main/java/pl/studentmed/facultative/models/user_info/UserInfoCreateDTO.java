package pl.studentmed.facultative.models.user_info;

import javax.validation.constraints.*;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
import pl.studentmed.facultative.models.validators.role.Role;

import java.time.LocalDate;

import static pl.studentmed.facultative.models.validators.ValueObject.NAME_REGEX;

@Validated
public record UserInfoCreateDTO(@NotNull
                                @Pattern(regexp = NAME_REGEX, message = "First name must contain only letters.")
                                @Size(min = 2, max = 30)
                                String firstName,
                                @NotNull
                                @Pattern(regexp = NAME_REGEX, message = "Last name must contain only letters.")
                                @Size(min = 2, max = 30)
                                String lastName,
                                @NotNull
                                @Size(max = 30)
                                @Email
                                String email,
                                @NotNull
                                @Size(min = 8, max = 25)
                                String password,
                                @NotNull
                                @Pattern(regexp = "\\d+", message = "Phone number must contain only digits.")
                                @Size(min = 9, max = 9)
                                String phoneNumber,
                                @NotNull
                                @Past
                                LocalDate birthdate,
                                @NotNull
                                @Pattern(regexp = "\\d+", message = "Pesel must contain only digits.")
                                @Size(min = 11, max = 11, message = "Pesel must be 11 characters length.")
                                String pesel,
                                @NotEmpty
                                @Role
                                String role) {

    @Builder public UserInfoCreateDTO {}

}
