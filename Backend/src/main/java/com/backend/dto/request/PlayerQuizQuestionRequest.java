package com.backend.dto.request;

import com.backend.model.AnswerOptionDto;
import com.backend.model.PlayerQuizDto;
import com.backend.model.QuestionTypeDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PlayerQuizQuestionRequest(
    @NotBlank String question,
    @NotNull QuestionTypeDto questionType,
    @NotNull AnswerOptionDto type,
    @NotNull PlayerQuizDto playerQuiz,
    boolean correct,
    int points,
    int timeTaken,
    @NotEmpty @Valid List<PlayerQuizAnswersRequest> playerQuizAnswers) {}
