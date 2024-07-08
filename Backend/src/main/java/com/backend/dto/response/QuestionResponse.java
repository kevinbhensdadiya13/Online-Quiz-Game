package com.backend.dto.response;

import com.backend.model.AnswerOptionDto;
import com.backend.model.QuestionTypeDto;
import java.util.List;

public record QuestionResponse(
    Long id,
    String question,
    int timeLimit,
    int points,
    QuestionTypeDto questionType,
    AnswerOptionDto answerOption,
    Long quizId,
    List<QuestionAnswerResponse> questionAnswers) {}
