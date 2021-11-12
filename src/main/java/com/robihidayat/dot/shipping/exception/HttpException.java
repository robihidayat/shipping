package com.robihidayat.dot.shipping.exception;

public class HttpException extends RuntimeException {
  private final int status;

  public HttpException(String message, int status) {
    super(message);
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
