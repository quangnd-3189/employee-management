package com.learnjiava.employee_management.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntegerValidator implements ConstraintValidator<ValidInteger, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) return true;

        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
