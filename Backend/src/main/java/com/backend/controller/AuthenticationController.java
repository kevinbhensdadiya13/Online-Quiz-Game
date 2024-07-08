package com.backend.controller;

import com.backend.constant.Constant;
import com.backend.dto.BaseResponse;
import com.backend.dto.request.LoginRequest;
import com.backend.dto.request.OtpRequest;
import com.backend.dto.response.LoginResponse;
import com.backend.dto.response.OtpResponse;
import com.backend.enums.ResultCode;
import com.backend.handler.AutheticationHandler;
import com.backend.handler.UserHandler;

import jakarta.validation.Valid;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final AutheticationHandler authenticationHandler;
  private final MessageSource messageSource;
  private final UserHandler userHandler;

  public AuthenticationController(
      AutheticationHandler authenticationHandler,
      MessageSource messageSource,
      UserHandler userHandler) {
    this.authenticationHandler = authenticationHandler;
    this.messageSource = messageSource;
    this.userHandler = userHandler;
  }

  @PostMapping("/login")
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    LoginResponse loginResponse = authenticationHandler.login(loginRequest);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "user.login.success.response",
            new Object[] {Constant.AUTHENTICATION},
            Locale.getDefault()),
        loginResponse);
  }

  @GetMapping("/refresh")
  public BaseResponse<String> refreshToken(@RequestHeader("Authorization") String token) {
    String refreshToken = authenticationHandler.refreshToken(token);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage("saved.data.success", new String[] {}, Locale.getDefault()),
        refreshToken);
  }

  @PostMapping("/logout")
  public BaseResponse<String> logout(@RequestHeader("Authorization") String token) {
    authenticationHandler.logout(token);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "user.logout.success.response", new String[] {}, Locale.getDefault()),
        null);
  }

  @GetMapping("/otp/generate/{id}")
  public BaseResponse<OtpResponse> generateOtp(@PathVariable Long id) {
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage("otp.generated.success", new Object[] {}, Locale.getDefault()),
        userHandler.generateOtp(id));
  }

  @PostMapping("/otp/verify")
  public BaseResponse<String> verifyOtp(@RequestBody @Valid OtpRequest otpRequest) {
    userHandler.verifyOtp(otpRequest);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage("otp.verified.success", new Object[] {}, Locale.getDefault()));
  }
}
