package org.odnokursniki.auth.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.Instant;


@Value
@Builder
public class ErrorResponse {

    int status;
    String title;
    String path;
    String error;

    @Builder.Default
    Instant timestamp = Instant.now();

    public static ErrorResponse of(String path, HttpStatus status, String message) {
        return ErrorResponse.builder()
                .path(path)
                .error(message)
                .status(status.value())
                .title(status.getReasonPhrase())
                .build();
    }
}
