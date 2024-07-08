package com.backend.handler;

import com.backend.dto.request.LoginRequest;
import com.backend.dto.response.LoginResponse;
import com.backend.mapper.UserCredentialMapper;
import com.backend.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AutheticationHandler {

  private final UserCredentialMapper userCredentialMapper;

  private final AuthenticationService authenticationService;

  public AutheticationHandler(
      UserCredentialMapper userCredentialMapper, AuthenticationService authenticationService) {
    this.userCredentialMapper = userCredentialMapper;
    this.authenticationService = authenticationService;
  }

  public LoginResponse login(LoginRequest loginRequest) {
    return authenticationService.authenticate(userCredentialMapper.toEntity(loginRequest));
  }

  public String refreshToken(String token) {
    return authenticationService.refreshToken(token);
  }

  public void logout(String token) {
    authenticationService.logout(token);
  }
}
