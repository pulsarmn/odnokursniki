package org.odnokursniki.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.odnokursniki.auth.validation.annotation.PhoneNumber;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^[\\d-\\s()]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(PHONE_NUMBER_PATTERN);
    }
}
