package it.reactive.academy.springMvc.exception;

public class CustomException extends RuntimeException {
  public CustomException(String message) {
    super(message);
  }
}
