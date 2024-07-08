package com.backend.service;

public interface BlacklistTokenService {

  void blacklistToken(String token);

  boolean isTokenBlacklisted(String token);

  void deleteExpiredTokens();
}
