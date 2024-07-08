package com.backend.dto.response;

import com.backend.model.AnswerOptionDto;
import com.backend.model.QuestionTypeDto;
import java.util.List;

public record PlayerQuizQuestionAnswerResponse(
    Long id,
    String question,
    QuestionTypeDto questionType,
    AnswerOptionDto type,
    List<PlayerQuizAnswerResponse> playerQuizAnswers) {}
