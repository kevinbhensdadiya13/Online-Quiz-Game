package com.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserPatchRequest(
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "{username.pattern.validation.error}")
        String username,
    @Email(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "{email.pattern.validation.error}")
        String email,
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$",
            message = "{password.pattern.validation.error}")
        String password) {}
