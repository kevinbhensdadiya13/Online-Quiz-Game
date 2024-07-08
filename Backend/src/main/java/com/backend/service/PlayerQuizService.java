package com.backend.service;

import com.backend.dto.request.GlobarSearchRequest;
import com.backend.dto.request.PlayerPinRequest;
import com.backend.dto.response.PinVerifyResponse;
import com.backend.entity.PlayerQuiz;
import java.util.List;
import org.springframework.data.domain.Page;

public interface PlayerQuizService {
  PlayerQuiz create(PlayerQuiz playerQuiz);

  PlayerQuiz getById(Long id);

  List<PlayerQuiz> getAll(Long userId);

  PlayerQuiz update(PlayerQuiz playerQuiz);

  void delete(Long id);

  boolean existsByQuizId(Long id);

  Page<PlayerQuiz> getReportBySearchName(GlobarSearchRequest request);

  long calculateTotalTime(PlayerQuiz playerQuiz);

  PinVerifyResponse verifyPin(PlayerPinRequest playerPinRequest);
}
