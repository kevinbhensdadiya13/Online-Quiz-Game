package com.backend.service;

import com.backend.entity.PointsConfig;
import java.util.List;

public interface PointsConfigService {
  PointsConfig create(PointsConfig pointsConfig);

  List<PointsConfig> getAll();

  PointsConfig getById(Long id);

  PointsConfig update(PointsConfig pointsConfig);

  void deleteById(Long id);
}
