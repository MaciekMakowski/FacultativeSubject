package pl.studentmed.facultative.models.validators.role;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Constraint(validatedBy = CheckRoleValidator.class)
@Retention(RUNTIME)
public @interface Role {

    String message() default "Role must be one of 'patient', 'doctor' or 'reception'.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
