package org.odnokursniki.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.odnokursniki.auth.validation.annotation.PhoneNumber;

@Slf4j
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^[\\d-\\s()]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.debug("Validation phone number: {}", value);
        boolean isValid = value.matches(PHONE_NUMBER_PATTERN);
        if (!isValid) {
            log.debug("Phone number '{}' is invalid", value);
        }
        return isValid;
    }
}
