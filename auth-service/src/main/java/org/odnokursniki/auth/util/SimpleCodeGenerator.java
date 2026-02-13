package org.odnokursniki.auth.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;


@Slf4j
@Component
public class SimpleCodeGenerator implements CodeGenerator {

    private final Random random = new Random();

    @Override
    public String generate() {
        int randomCode = random.nextInt(100_000, 999_999);
        log.debug("Code generator generated a random code: {}", randomCode);
        return String.valueOf(randomCode);
    }
}
