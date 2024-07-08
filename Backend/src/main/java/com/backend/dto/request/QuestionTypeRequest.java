package com.backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public record QuestionTypeRequest(@NotBlank String name) {}
