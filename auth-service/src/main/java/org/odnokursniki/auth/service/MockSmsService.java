package org.odnokursniki.auth.service;


import lombok.RequiredArgsConstructor;
import org.odnokursniki.auth.util.CodeGenerator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockSmsService implements SmsService {

    private final CodeGenerator codeGenerator;

    @Override
    public String send(String phoneNumber) {
        String code = codeGenerator.generate();
        System.out.println("{%s} verification code: %s".formatted(phoneNumber, code));
        return code;
    }
}
