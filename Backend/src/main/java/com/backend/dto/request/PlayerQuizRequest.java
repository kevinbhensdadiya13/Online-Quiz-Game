package com.backend.dto.request;

import com.backend.enums.PlayerQuizStatus;
import com.backend.model.QuizDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record PlayerQuizRequest(
    @NotNull
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "{name.pattern.error}")
        @Size(max = 30)
        String name,
    @NotNull QuizDto quiz,
    @Min(value = 0) int players,
    @NotNull LocalDateTime startDateTime,
    @NotNull LocalDateTime endDateTime,
    PlayerQuizStatus status) {}
