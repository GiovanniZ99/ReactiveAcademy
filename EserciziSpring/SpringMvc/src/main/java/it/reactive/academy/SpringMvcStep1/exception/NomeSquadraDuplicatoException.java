package it.reactive.academy.SpringMvcStep1.exception;

public class NomeSquadraDuplicatoException extends RuntimeException {
    private static final long serialVersionUID = -4346956288301342959L;

    public NomeSquadraDuplicatoException(String message) {
        super(message);
    }
}
