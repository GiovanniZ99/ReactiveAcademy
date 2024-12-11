package it.reactive.academy.SpringMvcStep1.exception;

public class SquadraNonTrovataException extends RuntimeException {

    private static final long serialVersionUID = -4346956288301342959L;


    public SquadraNonTrovataException(String message) {
        super(message);
    }
}
