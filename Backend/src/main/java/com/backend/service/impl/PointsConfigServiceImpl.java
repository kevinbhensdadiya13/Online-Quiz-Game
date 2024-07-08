package com.backend.service.impl;

import com.backend.constant.Constant;
import com.backend.entity.PointsConfig;
import com.backend.enums.ResultCode;
import com.backend.exception.OnlineQuizGameAppException;
import com.backend.mapper.PointconfigMapper;
import com.backend.repository.PointsConfigRepository;
import com.backend.service.PointsConfigService;
import com.backend.service.QuizService;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PointsConfigServiceImpl implements PointsConfigService {
  private final PointsConfigRepository pointsConfigRepository;
  private final MessageSource messageSource;
  private final PointconfigMapper pointconfigMapper;

  public PointsConfigServiceImpl(
      PointsConfigRepository pointsConfigRepository,
      MessageSource messageSource,
      PointconfigMapper pointconfigMapper) {
    this.pointsConfigRepository = pointsConfigRepository;
    this.messageSource = messageSource;
    this.pointconfigMapper = pointconfigMapper;
  }

  @Override
  public PointsConfig create(PointsConfig pointsConfig) {
    duplicateName(pointsConfig);
    return pointsConfigRepository.save(pointsConfig);
  }

  @Override
  public List<PointsConfig> getAll() {
    return pointsConfigRepository.findAll();
  }

  public PointsConfig getById(Long id) {
    return pointsConfigRepository
        .findById(id)
        .orElseThrow(
            () ->
                new OnlineQuizGameAppException(
                    ResultCode.DATA_NOT_FOUND.getCode(),
                    messageSource.getMessage(
                        "foreignkey.notexist",
                        new String[] {Constant.POINTS_CONFIG},
                        LocaleContextHolder.getLocale())));
  }

  @Override
  public PointsConfig update(PointsConfig pointsConfig) {
    PointsConfig existingEntity = getById(pointsConfig.getId());
    validate(pointsConfig);
    PointsConfig validEntity = pointconfigMapper.toDbEntity(pointsConfig, existingEntity);
    return pointsConfigRepository.save(validEntity);
  }

  private void validate(PointsConfig pointsConfig) {
    duplicateName(pointsConfig);
  }

  private void duplicateName(PointsConfig pointsConfig) {
    PointsConfig existingPointsConfigWithName =
        pointsConfigRepository.findByName(pointsConfig.getName());
    if (existingPointsConfigWithName != null
        && !existingPointsConfigWithName.getId().equals(pointsConfig.getId())) {
      throw new OnlineQuizGameAppException(
          ResultCode.QUIZ_ALREADY_EXIST.getCode(),
          messageSource.getMessage(
              "data.exists.error",
              new String[] {Constant.POINTS_CONFIG},
              LocaleContextHolder.getLocale()));
    }
  }

  public void deleteById(Long id) {
    pointsConfigRepository.deleteById(id);
  }
}
