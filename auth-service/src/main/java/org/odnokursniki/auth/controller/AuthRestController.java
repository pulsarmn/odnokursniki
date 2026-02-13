package org.odnokursniki.auth.controller;


import org.odnokursniki.auth.dto.SendCodeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @PostMapping("/request-code")
    public ResponseEntity<Void> requestVerificationCode(@Validated @RequestBody SendCodeRequest sendCodeRequest) {
        return ResponseEntity.ok().build();
    }
}
