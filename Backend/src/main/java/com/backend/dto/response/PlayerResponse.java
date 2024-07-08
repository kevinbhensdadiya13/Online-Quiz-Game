package com.backend.dto.response;

import com.backend.model.PlayerQuizDto;

public record PlayerResponse(
    Long id,
    String name,
    int ranking,
    int finalScore,
    boolean questionsAnswered,
    PlayerQuizDto playerQuiz) {}
