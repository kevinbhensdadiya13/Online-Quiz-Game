package com.backend.repository;

import com.backend.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
  QuestionType findByName(String name);
}
