package com.backend.mapper;

import com.backend.dto.request.AnswerOptionRequest;
import com.backend.dto.request.AnswerOptionUpdateRequest;
import com.backend.dto.response.AnswerOptionResponse;
import com.backend.entity.AnswerOption;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerOptionMapper {
  AnswerOption toEntity(AnswerOptionRequest answerOptionRequest);

  AnswerOptionResponse toResponse(AnswerOption answerOption);

  List<AnswerOptionResponse> toList(List<AnswerOption> answerOptionList);

  AnswerOption toEntity(AnswerOptionUpdateRequest answerOptionUpdateRequest);

  @Mapping(target = "id")
  AnswerOption toDbEntity(
      AnswerOption answerOption, @MappingTarget AnswerOption existingAnswerOption);
}
