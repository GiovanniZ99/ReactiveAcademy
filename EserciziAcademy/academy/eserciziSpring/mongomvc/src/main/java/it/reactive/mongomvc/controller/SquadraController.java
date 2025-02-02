package it.reactive.mongomvc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.reactive.mongomvc.dto.GiocatoreDTO;
import it.reactive.mongomvc.dto.SquadraDTO;
import it.reactive.mongomvc.dto.SquadraDiGiocatoriDTO;
import it.reactive.mongomvc.dto.TifoseriaDTO;
import it.reactive.mongomvc.exception.ErrorResponse;
import it.reactive.mongomvc.resource.SquadraResource;
import it.reactive.mongomvc.service.SquadraService;
import it.reactive.mongomvc.service.TifoseriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/squadre")
@Validated
public class SquadraController {

    private final SquadraService squadraService;

    private final TifoseriaService tifoseriaService;

    public SquadraController(SquadraService squadraService, TifoseriaService tifoseriaService) {
        this.squadraService = squadraService;
        this.tifoseriaService = tifoseriaService;
    }

    @Operation(summary = "Inserimento nuova squadra", description = "Aggiunge una nuova squadra al sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Squadra inserita con successo!"),
                    @ApiResponse(responseCode = "550", description = "C1 in caso di squadra già censita \n"
                            + "C6 in caso di errore di validazione",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/squadra")
    public ResponseEntity<SquadraResource> salvaSquadra(@Valid @RequestBody SquadraDTO squadraDto) {
        SquadraResource squadra = squadraService.create(squadraDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(squadra);
    }

    @Operation(summary = "Inserisci una squadra e la lista di giocatori")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "550", description = "C1 Squadra già censita\n"
                    + "C6 Errore di validazione", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("squadreGiocatori")
    public ResponseEntity<SquadraResource> salvaSquadraEGiocatori(@Valid @RequestBody @Parameter(description = "squadraGiocatori") SquadraDiGiocatoriDTO squadreDiGiocatoriDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(squadraService.createWithPlayers(squadreDiGiocatoriDTO));
    }

    @Operation(summary = "Restituisce la lista di squadre, con la lista completa di giocatori o meno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping
    public ResponseEntity<List<SquadraResource>> getSquadre(
            @Parameter(description = "Booleano che indica se restituire la lista completa di giocatori",
                    required = true)
            @RequestParam @Valid Boolean completo) {
        return ResponseEntity.ok(squadraService.read(completo));
    }

    @Operation(summary = "Aggiunge un giocatore a una squadra e restituisce la squadra aggiornata")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "550", description = "C3 Giocatore già censito | C4 Squadra non presente | C6 Errore di validazione",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/addGiocatore/{id}")
    public ResponseEntity<SquadraResource> addGiocatore(
            @Parameter(description = "ID della squadra", required = true)
            @PathVariable @Min(0) @Max(9999) Integer id,
            @Valid @RequestBody @Parameter(description = "Giocatore da aggiungere alla squadra") GiocatoreDTO giocatoreDTO) {
        return ResponseEntity.ok(squadraService.addPlayer(id, giocatoreDTO));
    }

    @Operation(summary = "Inserisce una tifoseria per una squadra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "550", description = "C4 Squadra non presente | C6 Errore di validazione",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/addTifoseria/{id}")
    public ResponseEntity<SquadraResource> aggiornaSquadraConTifoseria(
            @Parameter(description = "ID della squadra", required = true)
            @PathVariable @Min(0) @Max(9999) Integer id,
            @Valid @RequestBody @Parameter(description = "Tifoseria da aggiungere alla squadra") TifoseriaDTO tifoseriaDTO) {
        return ResponseEntity.ok(tifoseriaService.create(tifoseriaDTO, id));
    }

    @Operation(summary = "Rimuove una squadra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content | Squadra rimossa con successo"),
            @ApiResponse(responseCode = "404", description = "Squadra non trovata",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquadra(
            @Parameter(description = "ID della squadra da rimuovere", required = true)
            @PathVariable @Min(0) @Max(9999) Integer id) {
        squadraService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
