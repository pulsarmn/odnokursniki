package org.odnokursniki.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SendCodeRequest(

        @NotBlank
        @Pattern(regexp = "^\\+\\d{1,4}$", message = "Invalid country code")
        String countryCode,

        @NotBlank
        @Pattern(regexp = "^[\\d-\\s()]+$", message = "Phone number can only contain digits and formatting characters")
        String phoneNumber) {
}
