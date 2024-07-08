package com.backend.repository;

import com.backend.entity.PointsConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsConfigRepository extends JpaRepository<PointsConfig, Long> {
  PointsConfig findByName(String name);
}
