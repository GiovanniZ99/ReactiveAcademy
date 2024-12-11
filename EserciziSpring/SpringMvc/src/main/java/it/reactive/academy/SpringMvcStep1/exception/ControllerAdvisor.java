package it.reactive.academy.SpringMvcStep1.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NomeSquadraDuplicatoException.class)
    public ResponseEntity<Object> handleCustomNotFoundException(
            NomeSquadraDuplicatoException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        body.put("timestamp", formatDateTime);
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SquadraNonTrovataException.class)
    public ResponseEntity<Object> handleCustomNotFoundException(
            SquadraNonTrovataException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        body.put("timestamp", formatDateTime);
        body.put("message", ex.getMessage());

        return ResponseEntity.status(591).body(body);
    }

    @ExceptionHandler(TorneoNonTrovatoException.class)
    public ResponseEntity<Object> handleCustomNotFoundException(
            TorneoNonTrovatoException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        body.put("timestamp", formatDateTime);
        body.put("message", ex.getMessage());

        return ResponseEntity.status(592).body(body);
    }

//    @ExceptionHandler(CustomValidationException.class)
//    public ResponseEntity<Object> handleNodataFoundException(
//            CustomValidationException ex, WebRequest request) {
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formatDateTime = LocalDateTime.now().format(formatter);
//        body.put("timestamp", formatDateTime);
//        body.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Object> handleConstraintViolationException(
//            ConstraintViolationException ex) {
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formatDateTime = LocalDateTime.now().format(formatter);
//        body.put("timestamp", formatDateTime);
//        body.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formatDateTime = LocalDateTime.now().format(formatter);
//        body.put("timestamp", formatDateTime);
//        body.put("message", ex.getMessage());
//        body.put("errors", errors);
//        return handleExceptionInternal(
//                ex, body, headers, HttpStatus.BAD_REQUEST, request);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
    