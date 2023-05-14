package pl.studentmed.facultative.models.validators.pesel;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeselValidator.class)
public @interface Pesel {

    String message() default "Invalid PESEL number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
