package pl.studentmed.facultative.models.user_info;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record UserInfoUpdateDTO(
                                @NotNull
                                Long userInfoId,
                                @Pattern(regexp = "\\d+", message = "Phone number must contain only digits.")
                                @Size(min = 9, max = 9, message = "Phone number must contain exact 9 characters.")
                                String phoneNumber,
                                @NotNull(message = "Email field must be filled in.")
                                @Size(max = 30, message = "Email size cannot exceed 30 characters.")
                                @Email(message = "Email field must be a properly formatted email")
                                String email
) {
}
