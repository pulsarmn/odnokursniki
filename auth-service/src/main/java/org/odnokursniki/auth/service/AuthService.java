package org.odnokursniki.auth.service;


import lombok.RequiredArgsConstructor;
import org.odnokursniki.auth.dto.SendCodeRequest;
import org.odnokursniki.auth.exception.RateLimitingException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

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

        String attemptsKey = "verification:attempts:" + normalizedPhone;
        Long attempts = redisTemplate.opsForValue().increment(attemptsKey);
        if (attempts != null && attempts > MAX_ATTEMPTS) {
            redisTemplate.expire(attemptsKey, Duration.ofMinutes(10));
            throw new RateLimitingException("Too many requests");
        }

        String verificationCode = smsService.send(normalizedPhone);
        String verificationCodeKey = "verification:code:" + normalizedPhone;
        redisTemplate.opsForValue().set(verificationCodeKey, verificationCode, Duration.ofMinutes(10));
    }
}
