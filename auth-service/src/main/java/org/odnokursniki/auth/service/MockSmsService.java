package org.odnokursniki.auth.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.odnokursniki.auth.util.CodeGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


/**
 * {@link org.odnokursniki.auth.service.SmsService} development implementation
 * @since 0.1.0
 * @author pulsar
 */
@Slf4j
@Service
@Profile("dev")
@RequiredArgsConstructor
public class MockSmsService implements SmsService {

    private final CodeGenerator codeGenerator;

    /**
     * Logs the code to the console
     * @param phoneNumber client phone number
     * @return the sent code
     */
    @Override
    public String send(String phoneNumber) {
        String code = codeGenerator.generate();
        log.info("{} verification code: {}", phoneNumber, code);
        return code;
    }
}
