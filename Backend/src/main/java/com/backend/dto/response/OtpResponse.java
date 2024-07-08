package com.backend.dto.response;

import java.time.LocalDateTime;

public record OtpResponse(Long id, String otp, LocalDateTime expirationTime) {}
