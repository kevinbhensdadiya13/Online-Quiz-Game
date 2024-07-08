package com.backend.dto.response;

import java.time.LocalDateTime;

public record CollectionResponse(
    Long id, String name, LocalDateTime createdDate, LocalDateTime updatedDate) {}
