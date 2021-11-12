package com.robihidayat.dot.shipping.controller;

import com.robihidayat.dot.shipping.exception.CacheException;
import com.robihidayat.dot.shipping.exception.HttpException;
import com.robihidayat.dot.shipping.model.response.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = HttpException.class)
  public ResponseEntity handleBadRequest(HttpException ex) {
    ErrorResult body = new ErrorResult(ex.getStatus(), ex.getMessage());
    return ResponseEntity.status(ex.getStatus()).body(body);
  }

  @ExceptionHandler(value = CacheException.class)
  public ResponseEntity handleBadRequest(CacheException ex) {
    ErrorResult body = new ErrorResult(500, ex.getMessage());
    return ResponseEntity.status(500).body(body);
  }
}
