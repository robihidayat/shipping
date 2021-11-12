package com.robihidayat.dot.shipping.exception;

public class CacheException  extends RuntimeException{
  private final String key;

  public CacheException(String message, Throwable cause, String key) {
    super(message, cause);
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
