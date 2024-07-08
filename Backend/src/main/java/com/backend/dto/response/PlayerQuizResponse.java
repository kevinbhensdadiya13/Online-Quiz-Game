package com.backend.dto.response;

import com.backend.enums.PlayerQuizStatus;
import com.backend.model.QuizDto;
import com.backend.projection.PlayerQuizQuestionTypeCount;
import java.time.LocalDateTime;
import java.util.List;

public record PlayerQuizResponse(
    Long id,
    String name,
    QuizDto quiz,
    int players,
    double time,
    int code,
    LocalDateTime startDateTime,
    LocalDateTime endDateTime,
    Long totalQuestions,
    List<PlayerQuizQuestionTypeCount> playerQuizQuestionTypeCounts,
    PlayerQuizStatus status) {}
