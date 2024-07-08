package com.backend.dto.response;

import com.backend.model.AnswerOptionDto;
import com.backend.model.PlayerQuizDto;
import java.util.List;

public record PlayerQuizQuestionResponse(
    String question,
    AnswerOptionDto type,
    PlayerQuizDto playerQuiz,
    boolean correct,
    boolean noAnswer,
    int points,
    int timeTaken,
    List<PlayerQuizAnswerResponse> playerQuizAnswers) {}
