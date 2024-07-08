package com.backend.mapper;

import com.backend.dto.request.PlayerQuizAnswersRequest;
import com.backend.dto.response.PlayerQuizAnswerResponse;
import com.backend.entity.PlayerQuizAnswers;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PlayerQuizAnswerMapper {
  PlayerQuizAnswers toEntity(PlayerQuizAnswersRequest playerQuizAnswersRequest);

  PlayerQuizAnswerResponse toResponse(PlayerQuizAnswers playerQuizAnswers);
}
