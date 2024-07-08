package com.backend.mapper;

import com.backend.dto.request.OtpRequest;
import com.backend.dto.request.UserPatchRequest;
import com.backend.dto.request.UserRequest;
import com.backend.dto.request.UserUpdateRequest;
import com.backend.dto.response.OtpResponse;
import com.backend.dto.response.UserIdResponse;
import com.backend.dto.response.UserResponse;
import com.backend.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
  User toEntity(UserRequest userRequest);

  UserResponse toResponse(User user);

  List<UserResponse> toList(List<User> user);

  User toUpdateEntity(UserUpdateRequest userUpdateRequest);

  @Mapping(target = "id", source = "id")
  User toPatchEntity(Long id, UserPatchRequest userPatchRequest);

  @Mapping(target = "id", ignore = true)
  User toDbEntity(User user, @MappingTarget User existingEntity);

  User toEntity(OtpRequest otpRequest);

  OtpResponse toOtpResponse(User user);

  UserIdResponse toResponseId(User user);
}
