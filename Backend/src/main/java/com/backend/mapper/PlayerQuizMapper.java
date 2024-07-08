package com.backend.mapper;

import com.backend.dto.request.PlayerQuizRequest;
import com.backend.dto.request.PlayerQuizUpdateRequest;
import com.backend.dto.response.PlayerQuizResponse;
import com.backend.dto.response.QuizLibraryResponseRecord;
import com.backend.entity.PlayerQuiz;
import com.backend.projection.PlayerQuizQuestionTypeCount;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PlayerQuizMapper {
  PlayerQuizResponse toResponse(PlayerQuiz playerQuiz);

  PlayerQuiz toEntity(PlayerQuizRequest playerQuizRequest);

  List<PlayerQuizResponse> toList(List<PlayerQuiz> playerQuiz);

  List<QuizLibraryResponseRecord> toLibraryList(List<PlayerQuiz> quiz);

  PlayerQuiz toEntity(PlayerQuizUpdateRequest updateRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "code", ignore = true)
  PlayerQuiz toEntity(PlayerQuiz requestEntity, @MappingTarget PlayerQuiz existingEntity);

  @Mapping(target = "totalQuestions", source = "questionCount")
  @Mapping(target = "playerQuizQuestionTypeCounts", source = "questionTypeCounts")
  PlayerQuizResponse toPlayerQuizDetails(
      PlayerQuiz playerQuiz,
      Long questionCount,
      List<PlayerQuizQuestionTypeCount> questionTypeCounts);
}
