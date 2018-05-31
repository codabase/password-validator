package com.codabase.password.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Must have at least have one lowercase letter and one digit, can only contain lowercase and digit. Length must be between 5 and 12. No consecutive sequence of character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}