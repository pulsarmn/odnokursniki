package org.odnokursniki.auth.controller;


import lombok.RequiredArgsConstructor;
import org.odnokursniki.auth.dto.SendCodeRequest;
import org.odnokursniki.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/request-code")
    public ResponseEntity<Void> requestVerificationCode(@Validated @RequestBody SendCodeRequest sendCodeRequest) {
        authService.sendCode(sendCodeRequest);
        return ResponseEntity.ok().build();
    }
}
