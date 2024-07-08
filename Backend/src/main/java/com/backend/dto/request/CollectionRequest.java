package com.backend.dto.request;

import com.backend.model.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CollectionRequest(@NotBlank String name, @NotNull UserDto user) {}
