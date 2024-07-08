package com.backend.repository;

import com.backend.entity.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
  List<Collection> findByUserId(Long userId);
}
