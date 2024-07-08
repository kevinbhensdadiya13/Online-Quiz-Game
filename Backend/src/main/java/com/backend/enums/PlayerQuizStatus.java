package com.backend.enums;

import lombok.Getter;

@Getter
public enum PlayerQuizStatus {
  READY("READY"),
  IN_PROGRESS("IN_PROGRESS"),
  COMPLETED("COMPLETED");

  private String status;

  PlayerQuizStatus(String status) {
    this.status = status;
  }
}
