package com.learnjiava.employee_management.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = IntegerValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInteger {

    String message() default "Invalid integer value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
