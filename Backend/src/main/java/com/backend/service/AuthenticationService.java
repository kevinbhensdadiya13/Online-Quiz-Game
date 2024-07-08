package com.backend.service;

import com.backend.dto.response.LoginResponse;
import com.backend.entity.User;

public interface AuthenticationService {
  LoginResponse authenticate(User entity);

  String refreshToken(String token);

  void logout(String token);

  void verifyOtp(User user);

  User generateOtp(Long id);
}
