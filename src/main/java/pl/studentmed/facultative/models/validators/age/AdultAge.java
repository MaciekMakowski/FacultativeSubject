package pl.studentmed.facultative.models.validators.age;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultAgeValidator.class)
public @interface AdultAge {

    String message() default "Age must be at least 18 years old.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
