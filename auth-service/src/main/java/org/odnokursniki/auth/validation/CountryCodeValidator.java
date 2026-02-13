package org.odnokursniki.auth.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.odnokursniki.auth.validation.annotation.CountryCode;

public class CountryCodeValidator implements ConstraintValidator<CountryCode, String> {

    private static final String COUNTRY_CODE_PATTERN = "^\\+\\d{1,4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(COUNTRY_CODE_PATTERN);
    }
}
