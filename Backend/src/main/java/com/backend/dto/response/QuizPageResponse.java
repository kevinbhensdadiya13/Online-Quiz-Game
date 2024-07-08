package com.backend.dto.response;

import java.util.List;

public record QuizPageResponse(
    int pageNo, int pageSize, Long totalQuizzes, List<QuizLibraryResponseRecord> quizzes) {}
