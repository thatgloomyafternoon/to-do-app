package com.fw.todoapp.configurations;

import com.fw.todoapp.web.responses.Error;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorControllerAdvice {

  @ExceptionHandler({ConstraintViolationException.class})
  @ResponseBody
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
    List<String> messages = e.getConstraintViolations()
      .stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(new Error(messages.toString()));
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseBody
  public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    return ResponseEntity.badRequest().body(new Error(e.getMessage()));
  }

  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseBody
  public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.badRequest().body(new Error(e.getMessage()));
  }

}
