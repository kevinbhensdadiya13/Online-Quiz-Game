package com.backend.service;

import com.backend.entity.QuestionType;
import java.util.List;

public interface QuestionTypeService {
  QuestionType create(QuestionType questionType);

  List<QuestionType> getAll();

  QuestionType getById(Long id);

  QuestionType update(QuestionType questionType);

  void deleteById(Long id);
}
