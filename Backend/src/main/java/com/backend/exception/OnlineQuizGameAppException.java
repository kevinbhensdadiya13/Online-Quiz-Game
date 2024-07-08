package com.backend.exception;

import lombok.Getter;

@Getter
public class OnlineQuizGameAppException extends RuntimeException {
  private final int resultCode;
  private final String resultMessage;

  public OnlineQuizGameAppException(int resultCode, String resultMessage) {
    this.resultCode = resultCode;
    this.resultMessage = resultMessage;
  }
}
