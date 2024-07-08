package com.backend.service;

import com.backend.entity.Leaderboard;
import java.util.List;

public interface LeaderboardService {
  Leaderboard create(Leaderboard leaderboard);

  List<Leaderboard> getAll();

  Leaderboard getById(Long id);

  Leaderboard update(Leaderboard leaderboard);

  void delete(Long id);

  boolean existsByPlayerId(Long id);

  boolean existsByPlayerQuizId(Long id);
}
