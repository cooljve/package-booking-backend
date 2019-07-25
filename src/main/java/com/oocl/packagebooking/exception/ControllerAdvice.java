package com.oocl.packagebooking.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(PackageIsExistedException.class)
  public ResponseEntity handlePackageIsExistedException(PackageIsExistedException exception) {
    String str = "订单号已存在。";
    return ResponseEntity.badRequest().body(str);
  }

  @ExceptionHandler(PackageHasAlreadyBeenTakenException.class)
  public ResponseEntity handlePackageHasAlreadyBeenTakenException(PackageHasAlreadyBeenTakenException exception) {
    String str = "快件已被取。";
    return ResponseEntity.badRequest().body(str);
  }

  @ExceptionHandler(PackageNotExistedException.class)
  public ResponseEntity handlePackageNotExistedException(PackageNotExistedException exception) {
    String str = "快递单号不存在，请重新输入正确单号。";
    return ResponseEntity.badRequest().body(str);
  }

  @ExceptionHandler(TimeIsNotAllowException.class)
  public ResponseEntity handleTimeIsNotAllowException(TimeIsNotAllowException exception) {
    String str = "请在早上9点到晚上8点之间取件。";
    return ResponseEntity.badRequest().body(str);
  }
}
