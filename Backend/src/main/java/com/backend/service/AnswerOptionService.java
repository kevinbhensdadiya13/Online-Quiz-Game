package com.backend.service;

import com.backend.entity.AnswerOption;
import java.util.List;

public interface AnswerOptionService {
  AnswerOption create(AnswerOption answerOption);

  List<AnswerOption> getAll();

  AnswerOption getById(Long id);

  AnswerOption update(AnswerOption answerOption);

  void delete(Long id);
}
