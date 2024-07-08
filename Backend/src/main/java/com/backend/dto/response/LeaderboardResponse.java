package com.backend.dto.response;

import com.backend.model.PlayerDto;
import com.backend.model.PlayerQuizDto;

public record LeaderboardResponse(
    Long id, PlayerQuizDto playerQuiz, PlayerDto player, int points) {}
