package pl.studentmed.facultative.models.validators.role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import pl.studentmed.facultative.models.user_info.Role;

import java.util.Arrays;

public class CheckRoleValidator implements ConstraintValidator<pl.studentmed.facultative.models.validators.role.Role, String> {

    @Override
    public boolean isValid(String givenRole, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(Role.values())
                .anyMatch(role -> role.value.equals(givenRole));
    }

}
