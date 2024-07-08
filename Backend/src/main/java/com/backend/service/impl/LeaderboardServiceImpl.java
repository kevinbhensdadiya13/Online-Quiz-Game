package com.backend.service.impl;

import com.backend.entity.Leaderboard;
import com.backend.entity.Player;
import com.backend.entity.PlayerQuiz;
import com.backend.enums.ResultCode;
import com.backend.exception.OnlineQuizGameAppException;
import com.backend.mapper.LeaderboardMapper;
import com.backend.repository.LeaderboardRepository;
import com.backend.service.LeaderboardService;
import com.backend.service.PlayerQuizService;
import com.backend.service.PlayerService;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

  private final LeaderboardRepository repository;
  private final MessageSource messageSource;
  private final LeaderboardMapper leaderboardMapper;

  private final PlayerQuizService playerQuizService;
  private final PlayerService playerService;

  public LeaderboardServiceImpl(
      LeaderboardRepository repository,
      MessageSource messageSource,
      LeaderboardMapper leaderboardMapper,
      @Lazy PlayerQuizService playerQuizService,
      @Lazy PlayerService playerService) {
    this.repository = repository;
    this.messageSource = messageSource;
    this.leaderboardMapper = leaderboardMapper;
    this.playerQuizService = playerQuizService;
    this.playerService = playerService;
  }

  @Override
  public Leaderboard create(Leaderboard leaderboard) {
    PlayerQuiz playerQuiz = playerQuizService.getById(leaderboard.getPlayerQuiz().getId());
    leaderboard.setPlayerQuiz(playerQuiz);
    Player player = playerService.getById(leaderboard.getPlayer().getId());
    leaderboard.setPlayer(player);
    return repository.save(leaderboard);
  }

  @Override
  public List<Leaderboard> getAll() {
    return repository.findAll();
  }

  public Leaderboard getById(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () ->
                new OnlineQuizGameAppException(
                    ResultCode.DATA_NOT_FOUND.getCode(),
                    messageSource.getMessage(
                        "data.not.found", new String[] {}, LocaleContextHolder.getLocale())));
  }

  @Override
  public Leaderboard update(Leaderboard leaderboard) {
    Leaderboard existingLeaderboard = getById(leaderboard.getId());
    PlayerQuiz playerQuiz = playerQuizService.getById(leaderboard.getPlayerQuiz().getId());
    leaderboard.setPlayerQuiz(playerQuiz);
    Player player = playerService.getById(leaderboard.getPlayer().getId());
    leaderboard.setPlayer(player);
    Leaderboard updatedLeaderboard = leaderboardMapper.toDbEntity(leaderboard, existingLeaderboard);
    return repository.save(updatedLeaderboard);
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }

  public boolean existsByPlayerQuizId(Long id) {
    return repository.existsByPlayerQuizId(id);
  }

  public boolean existsByPlayerId(Long id) {
    return repository.existsByPlayerId(id);
  }
}
