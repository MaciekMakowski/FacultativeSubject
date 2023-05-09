package pl.studentmed.facultative.models.user_info;

import javax.validation.constraints.*;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
import pl.studentmed.facultative.models.validators.age.AdultAge;
import pl.studentmed.facultative.models.validators.pesel.Pesel;
import pl.studentmed.facultative.models.validators.role.Role;

import java.time.LocalDate;

import static pl.studentmed.facultative.models.validators.ValueObject.NAME_REGEX;

@Validated
public record UserInfoCreateDTO(@NotNull(message = "First name field must be filled in")
                                @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters.")
                                @Pattern(regexp = NAME_REGEX, message = "First name must contain only letters.")
                                String firstName,
                                @NotNull(message = "Last name field must be filled in.")
                                @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters.")
                                @Pattern(regexp = NAME_REGEX, message = "Last name must contain only letters.")
                                String lastName,
                                @NotNull(message = "Email field must be filled in.")
                                @Size(max = 30, message = "Email size cannot exceed 30 characters.")
                                @Email(message = "Email field must be a properly formatted email")
                                String email,
                                @NotNull(message = "Password field must be filled in.")
                                @Size(min = 8, max = 25, message = "Password must be between 8 and 25 characters.")
                                String password,
                                @NotNull(message = "Phone number field must be filled in.")
                                @Pattern(regexp = "\\d+", message = "Phone number must contain only digits.")
                                @Size(min = 9, max = 9, message = "Phone number must contain exact 9 characters.")
                                String phoneNumber,
                                @NotNull(message = "Birth date field must be filled in.")
                                @AdultAge
                                LocalDate birthdate,
                                @NotNull(message = "Pesel field must be filled in.")
                                @Pesel
                                String pesel,
                                @NotEmpty(message = "Role field must be filled in.")
                                @Role
                                String role) {

    @Builder public UserInfoCreateDTO {}

}
