package com.oocl.packagebooking.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(PackageIsExistedException.class)
  public ResponseEntity handlePackageIsExistedException(PackageIsExistedException exception) {
    String str = "订单号已存在";
    return ResponseEntity.badRequest().body(str);
  }
}
