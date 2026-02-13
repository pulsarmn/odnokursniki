package org.odnokursniki.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.odnokursniki.auth.validation.annotation.CountryCode;

public record SendCodeRequest(

        @NotBlank
        @CountryCode
        String countryCode,

        @NotBlank
        @Pattern(regexp = "^[\\d-\\s()]+$", message = "Phone number can only contain digits and formatting characters")
        String phoneNumber) {
}
