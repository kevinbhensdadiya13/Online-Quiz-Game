package com.backend.dto.request;

import com.backend.constant.Constant;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record QuizRequestByStatus(
    Long userId,
    @Pattern(
            regexp = "(?i)" + Constant.QUIZ_STATUS_CREATED + "|" + Constant.QUIZ_STATUS_DRAFT,
            message = "{invalid.status.error}")
        String status,
    @Min(0) Integer pageNo,
    @Min(1) Integer pageSize) {}
