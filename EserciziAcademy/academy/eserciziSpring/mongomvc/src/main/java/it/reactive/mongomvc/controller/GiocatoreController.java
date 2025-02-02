package it.reactive.mongomvc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.reactive.mongomvc.exception.ErrorResponse;
import it.reactive.mongomvc.resource.GiocatoreResource;
import it.reactive.mongomvc.service.GiocatoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/giocatori")
@Validated
public class GiocatoreController {
    private final GiocatoreService giocatoreService;

    public GiocatoreController(GiocatoreService giocatoreService) {
        this.giocatoreService = giocatoreService;
    }

    @Operation(summary = "Aumenta di 1 le ammonizioni del giocatore", description = "Incrementa il numero di ammonizioni del giocatore identificato dall'id fornito.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Giocatore aggiornato con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GiocatoreResource.class))),
            @ApiResponse(responseCode = "550", description = "Errore di validazione o il giocatore non esiste", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "ID non valido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/updateAmmonizioni/{id}")
    public ResponseEntity<GiocatoreResource> aumentaAmmonizioni(
            @Parameter(description = "ID del giocatore da aggiornare", required = true)
            @PathVariable @Min(value = 0, message = "L'ID deve essere positivo") @Max(value = 9999, message = "L'ID deve essere inferiore a 10000") Integer id) {
        return ResponseEntity.ok(giocatoreService.updateAmmonizioni(id));
    }
}

