package com.backend.service.impl;

import com.backend.constant.Constant;
import com.backend.entity.QuestionType;
import com.backend.enums.ResultCode;
import com.backend.exception.OnlineQuizGameAppException;
import com.backend.mapper.QuestionTypeMapper;
import com.backend.repository.QuestionTypeRepository;
import com.backend.service.QuestionTypeService;
import com.backend.service.QuizService;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

  private final QuestionTypeRepository questionTypeRepository;
  private final QuestionTypeMapper questionTypeMapper;
  private final MessageSource messageSource;

  public QuestionTypeServiceImpl(
      QuestionTypeRepository questionTypeRepository,
      QuestionTypeMapper questionTypeMapper,
      MessageSource messageSource) {
    this.questionTypeRepository = questionTypeRepository;
    this.questionTypeMapper = questionTypeMapper;
    this.messageSource = messageSource;
  }

  @Override
  public QuestionType create(QuestionType questionType) {
    duplicateName(questionType);
    return questionTypeRepository.save(questionType);
  }

  @Override
  public List<QuestionType> getAll() {
    return questionTypeRepository.findAll();
  }

  public QuestionType getById(Long id) {
    return questionTypeRepository
        .findById(id)
        .orElseThrow(
            () ->
                new OnlineQuizGameAppException(
                    ResultCode.DATA_NOT_FOUND.getCode(),
                    messageSource.getMessage(
                        "foreignkey.notexist",
                        new String[] {Constant.QUESTION_TYPE},
                        LocaleContextHolder.getLocale())));
  }

  @Override
  public QuestionType update(QuestionType questionType) {
    QuestionType existingEntity = getById(questionType.getId());
    validate(questionType);
    QuestionType validEntity = questionTypeMapper.toDbEntity(questionType, existingEntity);
    return questionTypeRepository.save(validEntity);
  }

  private void validate(QuestionType questionType) {
    duplicateName(questionType);
  }

  private void duplicateName(QuestionType questionType) {
    QuestionType existingQuiTypeWithName =
        questionTypeRepository.findByName(questionType.getName());
    if (existingQuiTypeWithName != null
        && !existingQuiTypeWithName.getId().equals(questionType.getId())) {
      throw new OnlineQuizGameAppException(
          ResultCode.QUIZ_ALREADY_EXIST.getCode(),
          messageSource.getMessage(
              "data.exists.error",
              new String[] {Constant.QUESTION_TYPE},
              LocaleContextHolder.getLocale()));
    }
  }

  public void deleteById(Long id) {
    questionTypeRepository.deleteById(id);
  }
}
