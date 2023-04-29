package pl.studentmed.facultative.models.user_info;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
public record UserInfoCreateDTO(@NotNull
                                @Size(min = 2, max = 30)
                                String firstName,
                                @NotNull
                                @Size(min = 2, max = 30)
                                String lastName,
                                @NotNull
                                @Size(max = 30)
                                @Email String email,
                                @NotNull
                                @Size(min = 8, max = 25)
                                String password,
                                @NotNull
                                @Pattern(regexp = "\\d+", message = "Phone number must contains only digits.")
                                @Size(min = 9, max = 9)
                                String phoneNumber,
                                @NotNull
                                @Past
                                LocalDate birthdate,
                                @NotNull
                                @Pattern(regexp = "\\d+", message = "Pesel must contains only digits.")
                                @Size(min = 11, max = 11, message = "Pesel must be 11 chars length.")
                                String pesel,
                                @NotEmpty String role) {

    @Builder public UserInfoCreateDTO {}

}
