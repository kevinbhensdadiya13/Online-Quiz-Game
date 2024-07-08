package com.backend.repository;

import com.backend.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
  boolean existsByPlayerId(Long id);

  boolean existsByPlayerQuizId(Long id);
}
