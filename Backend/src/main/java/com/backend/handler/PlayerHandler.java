package com.backend.handler;

import com.backend.dto.request.PlayerRequest;
import com.backend.dto.request.PlayerUpdateRequest;
import com.backend.dto.response.PlayerResponse;
import com.backend.entity.Player;
import com.backend.mapper.PlayerMapper;
import com.backend.projection.PlayerQuizQuestion;
import com.backend.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PlayerHandler {
  private static final Logger log = LoggerFactory.getLogger(PlayerHandler.class);
  private final PlayerService playerService;
  private final PlayerMapper playerMapper;

  public PlayerHandler(PlayerService playerService, PlayerMapper playerMapper) {
    this.playerService = playerService;
    this.playerMapper = playerMapper;
  }

  public PlayerResponse create(PlayerRequest playerRequest) {
    Player player = playerMapper.toEntity(playerRequest);
    return playerMapper.toResponse(playerService.create(player));
  }

  public Page<PlayerQuizQuestion> getPlayerByPlayerQuiz(Long playerQuizId, int page, int pageSize) {
    Page<PlayerQuizQuestion> playerPage =
        playerService.getPlayerByPlayerQuiz(playerQuizId, page, pageSize);

    Page<PlayerQuizQuestion> responsePage =
        new PageImpl<>(
            playerPage.getContent(),
            PageRequest.of(page - 1, pageSize),
            playerPage.getTotalElements());
    log.info(
        "Returning {} Players for playerQuizId: {}", responsePage.getTotalElements(), playerQuizId);

    return responsePage;
  }

  public PlayerResponse getById(Long id) {
    return playerMapper.toResponse(playerService.getById(id));
  }

  public PlayerResponse update(PlayerUpdateRequest playerUpdateRequest) {
    Player player = playerMapper.toEntity(playerUpdateRequest);
    return playerMapper.toResponse(playerService.update(player));
  }

  public void delete(Long id) {
    playerService.delete(id);
  }
}
