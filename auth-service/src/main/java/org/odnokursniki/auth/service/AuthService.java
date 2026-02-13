package org.odnokursniki.auth.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.odnokursniki.auth.dto.SendCodeRequest;
import org.odnokursniki.auth.exception.RateLimitingException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final SmsService smsService;
    private final PhoneNumberNormalizer phoneNormalizer;
    private final StringRedisTemplate redisTemplate;

    private static final int MAX_ATTEMPTS = 5;

    public void sendCode(SendCodeRequest sendCodeRequest) {
        String normalizedPhone = phoneNormalizer.normalize(
                sendCodeRequest.countryCode(),
                sendCodeRequest.phoneNumber());
        checkRateLimiting(normalizedPhone);

        log.debug("Sending verification code to '{}' phone...", normalizedPhone);
        String verificationCode = smsService.send(normalizedPhone);
        String verificationCodeKey = "verification:code:" + normalizedPhone;
        redisTemplate.opsForValue().set(verificationCodeKey, verificationCode, Duration.ofMinutes(10));
    }

    private void checkRateLimiting(String normalizedPhone) {
        String attemptsKey = "verification:code:attempts:" + normalizedPhone;
        Long attempts = redisTemplate.opsForValue().increment(attemptsKey);
        redisTemplate.expire(attemptsKey, Duration.ofMinutes(10));

        if (attempts != null && attempts > MAX_ATTEMPTS) {
            log.error("Too many requests on phone number: {}", normalizedPhone);
            throw new RateLimitingException("Too many requests");
        }
    }
}
