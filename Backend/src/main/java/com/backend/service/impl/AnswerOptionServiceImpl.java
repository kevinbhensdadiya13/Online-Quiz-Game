package com.backend.service.impl;

import com.backend.constant.Constant;
import com.backend.entity.AnswerOption;
import com.backend.enums.ResultCode;
import com.backend.exception.OnlineQuizGameAppException;
import com.backend.mapper.AnswerOptionMapper;
import com.backend.repository.AnswerOptionRepository;
import com.backend.service.AnswerOptionService;
import com.backend.service.PlayerQuizQuestionService;
import com.backend.service.QuestionService;
import java.util.LinkedList;
import java.util.List;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

  private final AnswerOptionRepository answerOptionRepository;
  private final AnswerOptionMapper answerOptionMapper;
  private final MessageSource messageSource;
  private final QuestionService questionService;
  private final PlayerQuizQuestionService playerQuizQuestionService;

  public AnswerOptionServiceImpl(
      AnswerOptionRepository repository,
      AnswerOptionMapper answerOptionMapper,
      MessageSource messageSource,
      @Lazy QuestionService questionService,
      @Lazy PlayerQuizQuestionService playerQuizQuestionService) {

    this.answerOptionRepository = repository;
    this.answerOptionMapper = answerOptionMapper;
    this.messageSource = messageSource;
    this.questionService = questionService;
    this.playerQuizQuestionService = playerQuizQuestionService;
  }

  @Override
  public AnswerOption create(AnswerOption answerOption) {
    duplicateName(answerOption);
    return answerOptionRepository.save(answerOption);
  }

  @Override
  public List<AnswerOption> getAll() {
    return answerOptionRepository.findAll();
  }

  @Override
  public AnswerOption getById(Long id) {
    return answerOptionRepository
        .findById(id)
        .orElseThrow(
            () ->
                new OnlineQuizGameAppException(
                    ResultCode.DATA_NOT_FOUND.getCode(),
                    messageSource.getMessage(
                        "foreignkey.notexist",
                        new String[] {Constant.ANSWER_OPTION},
                        LocaleContextHolder.getLocale())));
  }

  @Override
  public AnswerOption update(AnswerOption answerOption) {
    validate(answerOption);
    AnswerOption existingEntity = getById(answerOption.getId());
    AnswerOption updatedEntity = answerOptionMapper.toDbEntity(answerOption, existingEntity);
    return answerOptionRepository.save(updatedEntity);
  }

  public void delete(Long id) {
    validate(id);
    answerOptionRepository.deleteById(id);
  }

  private void validate(Long id) {
    boolean existIdQuestion = questionService.existsByQuestionTypeId(id);
    boolean existIdPlayerQuizQuestion = playerQuizQuestionService.existsByQuestionTypeId(id);

    List<String> error = new LinkedList<>();
    if (existIdQuestion) error.add(Constant.QUESTION);
    if (existIdPlayerQuizQuestion) error.add(Constant.PLAYER_QUIZ_QUESTION);

    if (!ObjectUtils.isEmpty(error)) {
      throw new OnlineQuizGameAppException(
          ResultCode.DATA_NOT_DELETED.getCode(),
          messageSource.getMessage(
              "data.not.delete",
              new String[] {Constant.ANSWER_OPTION, StringUtils.join(error, ',')},
              LocaleContextHolder.getLocale()));
    }
  }

  private void validate(AnswerOption id) {
    duplicateName(id);
  }

  private void duplicateName(AnswerOption answerOption) {
    AnswerOption existingQuiTypeWithName =
        answerOptionRepository.findByName(answerOption.getName());
    if (existingQuiTypeWithName != null
        && !existingQuiTypeWithName.getId().equals(answerOption.getId())) {
      throw new OnlineQuizGameAppException(
          ResultCode.ANSWER_OPTION_ALREADY_EXIST.getCode(),
          messageSource.getMessage(
              "data.exists.error",
              new String[] {Constant.ANSWER_OPTION},
              LocaleContextHolder.getLocale()));
    }
  }
}
