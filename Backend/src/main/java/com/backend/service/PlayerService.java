package com.backend.service;

import com.backend.entity.Player;
import com.backend.projection.PlayerQuizQuestion;
import org.springframework.data.domain.Page;

public interface PlayerService {
  Player create(Player player);

  Page<PlayerQuizQuestion> getPlayerByPlayerQuiz(Long playerQuizId, int page, int pageSize);

  Player getById(Long id);

  Player update(Player player);

  void delete(Long id);

  boolean existsByPlayerQuizId(Long id);
}
