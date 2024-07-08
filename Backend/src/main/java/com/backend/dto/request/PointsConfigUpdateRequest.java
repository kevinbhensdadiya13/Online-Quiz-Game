package com.backend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PointsConfigUpdateRequest(
    @NotNull @Min(value = 1, message = "{min.id.length}") Long id,
    @NotBlank String name,
    @NotNull String description) {}
