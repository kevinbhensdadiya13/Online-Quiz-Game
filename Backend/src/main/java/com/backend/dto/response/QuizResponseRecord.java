package com.backend.dto.response;

import com.backend.model.CollectionDto;
import com.backend.model.UserDto;

public record QuizResponseRecord(
    Long id,
    String name,
    String description,
    String coverImage,
    String status,
    UserDto user,
    CollectionDto collection) {}
