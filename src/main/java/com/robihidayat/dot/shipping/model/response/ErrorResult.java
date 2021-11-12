package com.robihidayat.dot.shipping.model.response;

import lombok.Getter;

@Getter
public class ErrorResult {
  private final Integer code;
  private final String message;

  public ErrorResult(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
