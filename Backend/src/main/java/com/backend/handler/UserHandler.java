package com.backend.handler;

import com.backend.dto.request.OtpRequest;
import com.backend.dto.request.UserPatchRequest;
import com.backend.dto.request.UserRequest;
import com.backend.dto.request.UserUpdateRequest;
import com.backend.dto.response.OtpResponse;
import com.backend.dto.response.UserIdResponse;
import com.backend.dto.response.UserResponse;
import com.backend.entity.User;
import com.backend.mapper.UserMapper;
import com.backend.service.AuthenticationService;
import com.backend.service.UserService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserHandler {
  private final UserMapper userMapper;
  private final UserService userService;
  private final AuthenticationService authenticationService;

  public UserHandler(
      UserService userService, UserMapper userMapper, AuthenticationService authenticationService) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.authenticationService = authenticationService;
  }

  public UserResponse add(UserRequest userRequest) {
    User user = userMapper.toEntity(userRequest);
    return userMapper.toResponse(userService.save(user));
  }

  public UserResponse getById(Long id) {
    return userMapper.toResponse(userService.getById(id));
  }

  public List<UserResponse> getAll() {
    return userMapper.toList(userService.getAll());
  }

  public UserResponse update(UserUpdateRequest userUpdateRequest) {
    User user = userMapper.toUpdateEntity(userUpdateRequest);
    return userMapper.toResponse(userService.update(user));
  }

  public UserResponse patchUpdate(Long id, UserPatchRequest userPatchRequest) {
    User user = userMapper.toPatchEntity(id, userPatchRequest);
    return userMapper.toResponse(userService.patchUpdate(user));
  }

  public void delete(Long id) {
    userService.delete(id);
  }

  public void verifyOtp(OtpRequest otpRequest) {
    authenticationService.verifyOtp(userMapper.toEntity(otpRequest));
  }

  public UserIdResponse findByUsername(String username) {
    return userMapper.toResponseId(userService.findByUsername(username));
  }

  public OtpResponse generateOtp(Long id) {
    return userMapper.toOtpResponse(authenticationService.generateOtp(id));
  }
}
