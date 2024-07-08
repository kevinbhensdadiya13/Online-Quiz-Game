package com.backend.dto.response;

import com.backend.model.CollectionDto;

public record QuizLibraryResponseRecord(
    String name,
    String coverImage,
    String status,
    Long id,
    Integer players,
    CollectionDto collection) {}
