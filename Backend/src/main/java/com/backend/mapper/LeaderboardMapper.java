package com.backend.mapper;

import com.backend.dto.request.LeaderboardRequest;
import com.backend.dto.request.LeaderboardUpdateRequest;
import com.backend.dto.response.LeaderboardResponse;
import com.backend.entity.Leaderboard;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LeaderboardMapper {

  Leaderboard toEntity(LeaderboardRequest leaderboardRequest);

  LeaderboardResponse toResponse(Leaderboard leaderboard);

  List<LeaderboardResponse> toList(List<Leaderboard> leaderboardList);

  Leaderboard toEntity(LeaderboardUpdateRequest leaderboardUpdateRequest);

  @Mapping(target = "id", ignore = true)
  Leaderboard toDbEntity(Leaderboard leaderboard, @MappingTarget Leaderboard existingLeaderboard);
}
