package org.odnokursniki.auth.util;

import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class SimpleCodeGenerator implements CodeGenerator {

    private final Random random = new Random();

    @Override
    public String generate() {
        int randomCode = random.nextInt(100_000, 999_999);
        return String.valueOf(randomCode);
    }
}
