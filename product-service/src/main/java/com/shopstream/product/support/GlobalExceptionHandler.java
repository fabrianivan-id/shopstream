package com.shopstream.product.support;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e) {
    return ResponseEntity.badRequest().body(Map.of("error", "validation", "message", e.getMessage()));
  }
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGeneric(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getClass().getSimpleName(), "message", e.getMessage()));
  }
}
