package org.odnokursniki.auth.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.odnokursniki.auth.dto.SendCodeRequest;
import org.odnokursniki.auth.exception.RateLimitingException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final SmsService smsService;
    private final PhoneNumberNormalizer phoneNormalizer;
    private final StringRedisTemplate redisTemplate;

    private static final int MAX_ATTEMPTS = 5;
    private static final String VERIFICATION_PREFIX = "verification:";

    public void sendCode(SendCodeRequest sendCodeRequest) {
        String normalizedPhone = phoneNormalizer.normalize(
                sendCodeRequest.countryCode(),
                sendCodeRequest.phoneNumber()
        );

        checkRateLimit(normalizedPhone);

        log.debug("Sending verification code to '{}' phone...", normalizedPhone);
        String verificationCode = smsService.send(normalizedPhone);
        String verificationRedisKey = VERIFICATION_PREFIX + normalizedPhone;

        redisTemplate.opsForHash().put(verificationRedisKey, "phoneNumber", normalizedPhone);
        redisTemplate.opsForHash().put(verificationRedisKey, "code", verificationCode);
        redisTemplate.expire(verificationRedisKey, Duration.ofMinutes(15));
    }

    private void checkRateLimit(String phoneNumber) {
        String verificationRedisKey = VERIFICATION_PREFIX + phoneNumber;
        Long attempts = redisTemplate.opsForHash().increment(verificationRedisKey, "attempts", 1);

        if (attempts != null && attempts > MAX_ATTEMPTS) {
            log.error("Too many requests on phone number: {}", phoneNumber);
            redisTemplate.opsForHash().expire(verificationRedisKey, Duration.ofMinutes(15), List.of("attempts"));
            throw new RateLimitingException("Too many requests");
        }
    }
}
