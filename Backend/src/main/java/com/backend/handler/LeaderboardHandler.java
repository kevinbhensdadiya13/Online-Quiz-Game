package com.backend.handler;

import com.backend.dto.request.LeaderboardRequest;
import com.backend.dto.request.LeaderboardUpdateRequest;
import com.backend.dto.response.LeaderboardResponse;
import com.backend.entity.Leaderboard;
import com.backend.mapper.LeaderboardMapper;
import com.backend.service.LeaderboardService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class LeaderboardHandler {
  private final LeaderboardService leaderboardService;
  private final LeaderboardMapper leaderboardMapper;

  public LeaderboardHandler(
      LeaderboardService leaderboardService, LeaderboardMapper leaderboardMapper) {
    this.leaderboardService = leaderboardService;
    this.leaderboardMapper = leaderboardMapper;
  }

  public LeaderboardResponse create(LeaderboardRequest leaderboardRequest) {
    Leaderboard leaderboard = leaderboardMapper.toEntity(leaderboardRequest);
    return leaderboardMapper.toResponse(leaderboardService.create(leaderboard));
  }

  public List<LeaderboardResponse> getAll() {
    return leaderboardMapper.toList(leaderboardService.getAll());
  }

  public LeaderboardResponse getById(Long id) {
    return leaderboardMapper.toResponse(leaderboardService.getById(id));
  }

  public LeaderboardResponse update(LeaderboardUpdateRequest leaderboardUpdateRequest) {
    Leaderboard leaderboard = leaderboardMapper.toEntity(leaderboardUpdateRequest);
    return leaderboardMapper.toResponse(leaderboardService.update(leaderboard));
  }

  public void delete(Long id) {
    leaderboardService.delete(id);
  }
}
