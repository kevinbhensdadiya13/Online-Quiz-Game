package com.backend.exception;

import com.backend.constant.Constant;
import com.backend.dto.BaseResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public BaseResponse<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), Constant.VALIDATION_ERROR, errors);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<BaseResponse<String>> handleIllegalArgumentExceptions(
      IllegalArgumentException ex) {
    return ResponseEntity.badRequest()
        .body(
            new BaseResponse<>(
                HttpStatus.BAD_REQUEST.value(), Constant.ILLEGAL_ARGUMENT_ERROR, ex.getMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<BaseResponse<String>> handleHttpMessageNotReadableExceptions(
      HttpMessageNotReadableException ex) {
    Throwable cause = ex.getCause();
      if (!(cause instanceof InvalidFormatException invalidFormatException)) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(
                  new BaseResponse<>(
                      HttpStatus.BAD_REQUEST.value(), Constant.ILLEGAL_ARGUMENT_ERROR, ex.getMessage()));
      }
      if (invalidFormatException.getTargetType().isEnum()) {
        return ResponseEntity.badRequest()
            .body(
                new BaseResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    Constant.ILLEGAL_ARGUMENT_ERROR,
                    "Status must be CREATED or DRAFT."));
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new BaseResponse<>(
                HttpStatus.BAD_REQUEST.value(), Constant.ILLEGAL_ARGUMENT_ERROR, ex.getMessage()));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<BaseResponse<String>> handleConstraintViolationExceptions(
      ConstraintViolationException ex) {
    return ResponseEntity.badRequest()
        .body(
            new BaseResponse<>(
                HttpStatus.BAD_REQUEST.value(), Constant.CONSTRAINT_ERROR, ex.getMessage()));
  }

  @ExceptionHandler(OnlineQuizGameAppException.class)
  @ResponseBody
  public ResponseEntity<BaseResponse<String>> handleOnlineQuizGameAppException(
      OnlineQuizGameAppException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            new BaseResponse<>(
                ex.getResultCode(), Constant.ERROR_CODE_PREFIX, ex.getResultMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponse<String>> handleAllExceptions(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            new BaseResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage()));
  }
}
