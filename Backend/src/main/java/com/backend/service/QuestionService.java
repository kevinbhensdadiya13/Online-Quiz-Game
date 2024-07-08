package com.backend.service;

import com.backend.entity.Question;
import com.backend.projection.PlayerQuizQuestionTypeCount;
import java.util.List;
import org.springframework.data.domain.Page;

public interface QuestionService {
  Question add(Question question);

  List<Question> getAll();

  Question getById(Long id);

  Question update(Question question);

  void delete(Long id);

  boolean existsByQuestionTypeId(Long id);

  boolean existsByQuizId(Long id);

  Page<Question> getQuestionsByQuizId(Long quizId, int page, int pageSize);

  Long getQuestionCountByQuiz(Long quizId);

  List<PlayerQuizQuestionTypeCount> getCountByQuestionType(Long quizId);
}
