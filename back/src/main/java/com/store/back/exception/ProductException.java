package com.store.back.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
public class ProductException extends Exception {

  private int code;

  private String message;

  private String extraInfo;

  public ProductException(int code, String message) {
    super(message);
    this.code = code;
    this.message = message;
    this.extraInfo = "";
  }

  public ProductException(String message) {
    super(message);
    this.message = message;
    this.extraInfo = "";
  }
}