package org.odnokursniki.auth.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.odnokursniki.auth.validation.annotation.CountryCode;

@Slf4j
public class CountryCodeValidator implements ConstraintValidator<CountryCode, String> {

    private static final String COUNTRY_CODE_PATTERN = "^\\+\\d{1,4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.debug("Validating country code: {}", value);
        boolean isValid = value.matches(COUNTRY_CODE_PATTERN);
        if (!isValid) {
            log.debug("Country code '{}' is invalid", value);
        }
        return isValid;
    }
}
