package com.backend.mapper;

import com.backend.dto.request.QuestionTypeRequest;
import com.backend.dto.request.QuestionTypeUpdateRequest;
import com.backend.dto.response.QuestionTypeResponse;
import com.backend.entity.QuestionType;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionTypeMapper {
  QuestionType toEntity(QuestionTypeRequest questionTypeRequest);

  QuestionTypeResponse toResponse(QuestionType questionType);

  List<QuestionTypeResponse> toList(List<QuestionType> questionTypeList);

  QuestionType toEntity(QuestionTypeUpdateRequest questionTypeUpdateRequest);

  @Mapping(target = "id", ignore = true)
  QuestionType toDbEntity(QuestionType questionType, @MappingTarget QuestionType existingQuiztype);
}
