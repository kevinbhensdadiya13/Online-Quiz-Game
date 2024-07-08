package com.backend.dto.request;

import jakarta.validation.constraints.Size;

public record QuestionAnswerRequest(
    @Size(max = 3000, message = "Option length cannot exceed {max} characters")
        String questionOption,
    boolean correct) {}
