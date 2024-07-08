package com.backend.service;

import com.backend.dto.request.GlobarSearchRequest;
import com.backend.dto.request.QuizPaginationRequest;
import com.backend.dto.request.QuizRequestByStatus;
import com.backend.entity.Quiz;
import java.util.List;
import org.springframework.data.domain.Page;

public interface QuizService {
  Quiz save(Quiz quiz);

  Quiz getQuizById(Long id);

  List<Quiz> getAll();

  Quiz update(Quiz quiz);

  void delete(Long id);

  boolean existsByUserId(Long id);

  boolean existsByCollectionId(Long id);

  Page<Quiz> getQuizzesByCollectionAndUser(QuizPaginationRequest request);

  Page<Quiz> getQuizzesByUserAndStatus(QuizRequestByStatus quizRequestbyStatus);

  Page<Quiz> getQuizBySearchName(GlobarSearchRequest request);
}
