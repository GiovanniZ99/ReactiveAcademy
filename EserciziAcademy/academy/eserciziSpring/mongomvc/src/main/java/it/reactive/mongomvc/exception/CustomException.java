package it.reactive.mongomvc.exception;

public class CustomException extends RuntimeException {
  public CustomException(String message) {
    super(message);
  }
}
