package org.odnokursniki.auth.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.odnokursniki.auth.util.CodeGenerator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MockSmsService implements SmsService {

    private final CodeGenerator codeGenerator;

    @Override
    public String send(String phoneNumber) {
        String code = codeGenerator.generate();
        log.info("{} verification code: {}", phoneNumber, code);
        return code;
    }
}
