package com.backend.controller;

import com.backend.constant.Constant;
import com.backend.dto.BaseResponse;
import com.backend.dto.request.QuestionTypeRequest;
import com.backend.dto.request.QuestionTypeUpdateRequest;
import com.backend.dto.response.QuestionTypeResponse;
import com.backend.enums.ResultCode;
import com.backend.handler.QuestionTypeHandler;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question/types")
public class QuestionTypeController {
  private final QuestionTypeHandler questionTypeHandler;
  private final MessageSource messageSource;

  public QuestionTypeController(
      QuestionTypeHandler questionTypeHandler, MessageSource messageSource) {
    this.questionTypeHandler = questionTypeHandler;
    this.messageSource = messageSource;
  }

  @PostMapping
  public BaseResponse<QuestionTypeResponse> create(
      @Valid @RequestBody QuestionTypeRequest questionTypeRequest) {
    QuestionTypeResponse response = questionTypeHandler.create(questionTypeRequest);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "saved.data.success", new Object[] {Constant.QUESTION_TYPE}, Locale.getDefault()),
        response);
  }

  @GetMapping
  public BaseResponse<List<QuestionTypeResponse>> getAll() {
    List<QuestionTypeResponse> response = questionTypeHandler.getAll();
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "found.data.success", new Object[] {Constant.QUESTION_TYPE}, Locale.getDefault()),
        response);
  }

  @GetMapping("/{id}")
  public BaseResponse<QuestionTypeResponse> getById(@PathVariable Long id) {
    QuestionTypeResponse response = questionTypeHandler.getById(id);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "found.data.success", new Object[] {Constant.QUESTION_TYPE}, Locale.getDefault()),
        response);
  }

  @PutMapping
  public BaseResponse<QuestionTypeResponse> update(
      @RequestBody @Valid QuestionTypeUpdateRequest questionTypeUpdateRequest) {
    QuestionTypeResponse response = questionTypeHandler.update(questionTypeUpdateRequest);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "updated.data.success", new Object[] {Constant.QUESTION_TYPE}, Locale.getDefault()),
        response);
  }

  @DeleteMapping("/{id}")
  public BaseResponse<String> deleteById(@PathVariable Long id) {
    questionTypeHandler.deleteById(id);
    return new BaseResponse<>(
        ResultCode.SUCCESS.getCode(),
        messageSource.getMessage(
            "delete.data.success", new Object[] {Constant.QUESTION_TYPE}, Locale.getDefault()));
  }
}
