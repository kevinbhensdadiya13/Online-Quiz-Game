package com.backend.repository;

import com.backend.entity.PlayerQuiz;
import com.backend.enums.PlayerQuizStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerQuizRepository extends JpaRepository<PlayerQuiz, Long> {

  boolean existsByQuizId(Long id);

  Optional<PlayerQuiz> findByStatusAndCode(PlayerQuizStatus status, int code);

  Page<PlayerQuiz> findByNameContainingIgnoreCaseAndUserId(
      String name, Long userId, Pageable pageable);

  List<PlayerQuiz> findByUserId(Long userId);

  Optional<PlayerQuiz> findByStatusInAndCode(List<PlayerQuizStatus> statuses, int code);
}
