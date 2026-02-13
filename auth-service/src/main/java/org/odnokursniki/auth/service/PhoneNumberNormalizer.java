package org.odnokursniki.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.odnokursniki.auth.exception.InvalidPhoneNumberException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Slf4j
@Component
public class PhoneNumberNormalizer {

    private static final String PREFIX = "+";

    public String normalize(String countryCode, String phoneNumber) {
        if (!StringUtils.hasText(countryCode) || !StringUtils.hasText(phoneNumber)) {
            log.error("Invalid phone number: CountryCode({}), PhoneNumber({})", countryCode, phoneNumber);
            throw new InvalidPhoneNumberException();
        }

        String cleanedNumber = phoneNumber.replaceAll("\\D", "");
        String cleanedCountryCode = countryCode.trim();
        if (!cleanedCountryCode.startsWith(PREFIX)) {
            cleanedCountryCode = PREFIX + cleanedCountryCode;
        }

        return cleanedCountryCode + cleanedNumber;
    }
}
