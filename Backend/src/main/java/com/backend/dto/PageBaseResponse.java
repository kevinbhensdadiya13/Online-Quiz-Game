package com.backend.dto;

import java.util.List;

public record PageBaseResponse<T>(
    int resultCode,
    String resultMessage,
    int page,
    int pageSize,
    long totalElements,
    List<T> data) {
}
