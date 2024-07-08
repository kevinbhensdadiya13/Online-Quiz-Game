package com.backend.controller;

import com.backend.constant.Constant;
import com.backend.dto.BaseResponse;
import com.backend.dto.request.PlayerPinRequest;
import com.backend.dto.request.PlayerQuizRequest;
import com.backend.dto.request.PlayerQuizUpdateRequest;
import com.backend.dto.response.PinVerifyResponse;
import com.backend.dto.response.PlayerQuizResponse;
import com.backend.enums.ResultCode;
import com.backend.handler.PlayerQuizHandler;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The PlayerQuizController class handles HTTP requests for managing player quizzes. */
@RestController
@RequestMapping("/api/player/quiz")
public class PlayerQuizController {
  private final PlayerQuizHandler playerQuizHandler;
  private final MessageSource messageSource;

  public PlayerQuizController(PlayerQuizHandler playerQuizHandler, MessageSource messageSource) {
    this.playerQuizHandler = playerQuizHandler;
    this.messageSource = messageSource;
  }

  @PostMapping
  public BaseResponse<PlayerQuizResponse> add(
      @Valid @RequestBody PlayerQuizRequest playerQuizRequest) {
    PlayerQuizResponse response = playerQuizHandler.create(playerQuizRequest);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "saved.data.success",
            new Object[] {Constant.PLAYER_QUIZ},
            LocaleContextHolder.getLocale()),
        response);
  }

  @GetMapping("/{id}")
  public BaseResponse<PlayerQuizResponse> get(@PathVariable("id") Long id) {
    PlayerQuizResponse response = playerQuizHandler.getById(id);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "player.quiz.found.success", new Object[] {}, LocaleContextHolder.getLocale()),
        response);
  }

  @GetMapping("/user/{userId}")
  public BaseResponse<List<PlayerQuizResponse>> getAll(@PathVariable("userId") Long userId) {
    List<PlayerQuizResponse> response = playerQuizHandler.getAll(userId);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "player.quiz.found.success", new Object[] {}, LocaleContextHolder.getLocale()),
        response);
  }

  @PutMapping
  public BaseResponse<PlayerQuizResponse> update(
      @Valid @RequestBody PlayerQuizUpdateRequest updateRequest) {
    PlayerQuizResponse updatedPlayerQuiz = playerQuizHandler.update(updateRequest);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "updated.data.success",
            new Object[] {Constant.PLAYER_QUIZ},
            LocaleContextHolder.getLocale()),
        updatedPlayerQuiz);
  }

  @DeleteMapping("/{id}")
  public BaseResponse<Void> delete(@PathVariable Long id) {
    playerQuizHandler.delete(id);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "delete.data.success",
            new Object[] {Constant.PLAYER_QUIZ},
            LocaleContextHolder.getLocale()),
        null);
  }

  @PostMapping("/pin/verify")
  public BaseResponse<PinVerifyResponse> verifyPin(
      @RequestBody @Valid PlayerPinRequest playerPinRequest) {
    PinVerifyResponse response = playerQuizHandler.verifyPin(playerPinRequest);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage("pin.verified.success", new Object[] {}, Locale.getDefault()),
        response);
  }
}
