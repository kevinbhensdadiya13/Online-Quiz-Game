package com.backend.mapper;

import com.backend.dto.request.QuizRequestRecord;
import com.backend.dto.request.QuizUpdateRequest;
import com.backend.dto.response.QuizLibraryResponseRecord;
import com.backend.dto.response.QuizResponseRecord;
import com.backend.entity.Quiz;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizMapper {
  Quiz toEntity(QuizRequestRecord quizRequest);

  QuizResponseRecord toResponse(Quiz quiz);

  List<QuizResponseRecord> toList(List<Quiz> quiz);

  List<QuizLibraryResponseRecord> toLibraryList(List<Quiz> quiz);

  Quiz toEntity(QuizUpdateRequest quizUpdateRequest);

  @Mapping(target = "id", ignore = true)
  Quiz toEntity(Quiz quiz, @MappingTarget Quiz existingEntity);
}
