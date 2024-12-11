package it.reactive.academy.springMvc.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SquadraGiaCensitaException.class)
    public ResponseEntity<Object> handleSquadraGiaCensitaException(SquadraGiaCensitaException ex) {
        return metodoResponse("C1", "Squadra già censita");
    }

    @ExceptionHandler(SquadraNonPresenteException.class)
    public ResponseEntity<Object> handleSquadraNonPresenteException(SquadraNonPresenteException ex) {
        return metodoResponse("C4", "Squadra non trovata");
    }

    @ExceptionHandler(GiocatoreGiaCensitoException.class)
    public ResponseEntity<Object> handleGiocatoreGiaCensitoException(GiocatoreGiaCensitoException ex) {
        return metodoResponse("C3", "Giocatore già censito");
    }

    @ExceptionHandler(TorneoNonTrovatoException.class)
    public ResponseEntity<Object> handleTorneoNonTrovatoException(TorneoNonTrovatoException ex) {
        return metodoResponse("C2", "Torneo non trovato");
    }

    @ExceptionHandler(SquadraNonTrovataException.class)
    public ResponseEntity<Object> handleSquadraNonTrovataException(SquadraNonTrovataException ex) {
        return metodoResponse("C5", "Giocatore non trovato");
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formatDateTime = LocalDateTime.now().format(formatter);
//        body.put("timestamp:", formatDateTime);
//        body.put("error", "C6");
//        body.put("message", ex.getMessage());
        ErrorResponse er = new ErrorResponse("C6", ex.getMessage());
        return ResponseEntity.status(550).body(er);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCod("C6");
        errorResponse.setDes("Validation failed: " + String.join(", ", errors));
        return ResponseEntity.status(550).body(errorResponse);
    }

    private ResponseEntity<Object> metodoResponse(String codice, String des) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCod(codice);
        errorResponse.setDes(des);
        return ResponseEntity.status(550).body(errorResponse);
    }
}
    