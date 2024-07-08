package com.backend.dto.request;

import com.backend.model.PlayerDto;
import com.backend.model.PlayerQuizDto;
import jakarta.validation.constraints.NotNull;

public record LeaderboardRequest(
    @NotNull PlayerQuizDto playerQuiz, @NotNull PlayerDto player, int points) {}
