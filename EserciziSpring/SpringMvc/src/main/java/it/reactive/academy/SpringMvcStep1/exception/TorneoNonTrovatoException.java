package it.reactive.academy.SpringMvcStep1.exception;

public class TorneoNonTrovatoException extends RuntimeException {

  private static final long serialVersionUID = -4346956288301342959L;


  public TorneoNonTrovatoException(String message) {
    super(message);
  }
}
