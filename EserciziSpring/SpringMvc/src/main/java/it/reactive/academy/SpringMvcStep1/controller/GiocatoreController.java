package it.reactive.academy.SpringMvcStep1.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.academy.SpringMvcStep1.exception.ErrorResponse;
import it.reactive.academy.SpringMvcStep1.resource.Giocatore;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "giocatori", produces = {MediaType.APPLICATION_JSON_VALUE, "application/json"})
@Validated
public class GiocatoreController {

    @ApiOperation(value = "Aumenta di 1 le ammonizioni", response = Giocatore.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Giocatore aggiornato con successo"),
            @ApiResponse(code = 550, message = "Il servizio va in errore con i cod:\n\n" +
                    "- C6 in caso di errore di validazione",
                    response = ErrorResponse.class)
    })
    @PutMapping("/updateAmmonizioni/{id}")
    public ResponseEntity<Giocatore> aumentaAmmonizioni(@Valid @PathVariable @ApiParam(value = "id giocatore", required = true)
                                                        @Min(value = 0, message = "L'ID deve essere positivo")
                                                        @Max(value = 9999, message = "L'ID deve essere inferiore a 10000") Long id) {
        return ResponseEntity.ok(new Giocatore());
    }
}
