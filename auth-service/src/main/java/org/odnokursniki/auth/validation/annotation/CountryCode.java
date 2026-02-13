package org.odnokursniki.auth.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.odnokursniki.auth.validation.CountryCodeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CountryCodeValidator.class})
public @interface CountryCode {

    String message() default "Invalid country code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
