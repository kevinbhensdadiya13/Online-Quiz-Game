package com.backend.mapper;

import com.backend.dto.request.QuestionAnswerRequest;
import com.backend.dto.response.QuestionAnswerResponse;
import com.backend.entity.QuestionAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionAnswerMapper {

  QuestionAnswer toEntity(QuestionAnswerRequest questionAnswerRequest);

  QuestionAnswerResponse toResponse(QuestionAnswer questionAnswer);
}
