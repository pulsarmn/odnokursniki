package org.odnokursniki.auth.dto;

import jakarta.validation.constraints.NotBlank;
import org.odnokursniki.auth.validation.annotation.CountryCode;
import org.odnokursniki.auth.validation.annotation.PhoneNumber;

public record SendCodeRequest(

        @NotBlank
        @CountryCode
        String countryCode,

        @NotBlank
        @PhoneNumber
        String phoneNumber) {
}
