package it.reactive.academy.SpringMvcStep1.exception;

public class CustomException extends RuntimeException {
  public CustomException(String message) {
    super(message);
  }
}
