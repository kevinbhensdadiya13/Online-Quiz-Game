package com.backend.mapper;

import com.backend.dto.request.QuestionRequest;
import com.backend.dto.request.QuestionUpdateRequest;
import com.backend.dto.response.QuestionResponse;
import com.backend.entity.Question;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    uses = QuestionAnswerMapper.class,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {
  Question toEntity(QuestionRequest questionRequest);

  Question toEntity(QuestionUpdateRequest questionUpdateRequest);

  @Mapping(target = "id", ignore = true)
  Question toDbEntity(Question question, @MappingTarget Question existingQuestion);

  QuestionResponse toResponse(Question question);

  List<QuestionResponse> toList(List<Question> questionList);
}
