package pl.studentmed.facultative.models.user_info;

import pl.studentmed.facultative.models.validators.age.AdultAge;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDate;

import static pl.studentmed.facultative.models.validators.ValueObject.NAME_REGEX;

public record UserInfoUpdateDTO(
                                @NotNull
                                Long userInfoId,
                                @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters.")
                                @Pattern(regexp = NAME_REGEX, message = "First name must contain only letters.")
                                String firstName,
                                @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters.")
                                @Pattern(regexp = NAME_REGEX, message = "Last name must contain only letters.")
                                String lastName,
                                @Pattern(regexp = "\\d+", message = "Phone number must contain only digits.")
                                @Size(min = 9, max = 9, message = "Phone number must contain exact 9 characters.")
                                String phoneNumber,
                                @AdultAge
                                LocalDate birthdate
) {
}
