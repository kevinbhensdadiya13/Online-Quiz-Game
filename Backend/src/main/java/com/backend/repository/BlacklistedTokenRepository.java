package com.backend.repository;

import com.backend.entity.BlacklistedToken;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {

  boolean existsByToken(String token);

  List<BlacklistedToken> findByExpiryAtBefore(LocalDateTime expiryDate);
}
