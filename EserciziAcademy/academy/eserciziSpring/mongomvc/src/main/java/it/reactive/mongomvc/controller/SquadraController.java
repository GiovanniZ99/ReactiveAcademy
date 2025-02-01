package it.reactive.mongomvc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import it.reactive.mongomvc.dto.SquadraDTO;
import it.reactive.mongomvc.exception.ErrorResponse;
import it.reactive.mongomvc.resource.SquadraResource;
import it.reactive.mongomvc.service.SquadraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "squadre", produces = {MediaType.APPLICATION_JSON_VALUE, "application/json"})
@Validated
public class SquadraController {

    private final SquadraService squadraService;

//    private final TifoseriaService tifoseriaService;

    public SquadraController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @Operation(
            summary = "Inserimento nuova squadra",
            description = "Aggiunge una nuova squadra al sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Squadra inserita con successo!"),
                    @ApiResponse(responseCode = "550", description = "C1 in caso di squadra già censita \n"
                            + "C6 in caso di errore di validazione",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping("/squadra")
    public ResponseEntity<SquadraResource> salvaSquadra(@Valid @RequestBody SquadraDTO squadraDto){
        SquadraResource squadra = squadraService.create(squadraDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(squadra);
    }

//    @ApiOperation(value = "Inserisci una squadra e la lista di giocatori", response = Squadra.class)
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK"), @ApiResponse(code = 550, message = "C1 Squadra già censita\n" + "C6 Errore di validazione", response = ErrorResponse.class)})
//    @PostMapping("squadreGiocatori")
//    public ResponseEntity<Squadra> salvaSquadraEGiocatori(@Valid @RequestBody @ApiParam(value = "squadraGiocatori") SquadraDiGiocatoriDTO squadreDiGiocatoriDTO) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(squadraService.createWithPlayers(squadreDiGiocatoriDTO));
//    }

//    @ApiOperation(value = "restituisce la lista di squadre, con la lista completa di giocatori o meno", response = Squadra.class, responseContainer = "List")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK")})
//    @GetMapping
//    public ResponseEntity<List<Squadra>> getSquadre(@Valid @RequestParam @ApiParam(value = "booleano", required = true) Boolean completo) {
//        return ResponseEntity.ok(squadraService.read(completo));
//    }
//
//    @ApiOperation(value = "aggiunge giocatore a squadra e restituisce la squadra aggiornata", response = Squadra.class)
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK"), @ApiResponse(code = 550, message = "C3 Giocatore già censito\n" + "C4 Squadra non presente \n" + "C6 Errore di validazione", response = ErrorResponse.class)})
//    @PutMapping("/addGiocatore/{id}")
//    public ResponseEntity<Squadra> addGiocatore(@PathVariable @ApiParam(value = "id squadra", required = true) @Min(value = 0) @Max(value = 9999) Integer id, @Valid @RequestBody @ApiParam(value = "giocatoreDTO") GiocatoreDTO giocatoreDTO) {
//        return ResponseEntity.ok(squadraService.addPlayer(id, giocatoreDTO));
//    }
//
//    @ApiOperation(value = "Inserisce una tifoseria", response = Tifoseria.class)
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK"), @ApiResponse(code = 550, message = "C4 Squadra non presente \n" +
//            "C6 Errore di validazione", response = ErrorResponse.class)})
//    @PutMapping("/addTifoseria/{id}")
//    public ResponseEntity<Tifoseria> aggiornaSquadraConTifoseria(@PathVariable @ApiParam(value = "id squadra", required = true) @Min(value = 0) @Max(value = 9999) Integer id,
//                                                               @Valid @RequestBody TifoseriaDTO tifoseriaDTO) {
//        return ResponseEntity.ok(tifoseriaService.create(tifoseriaDTO,id));
//    }
//
//    @ApiOperation(value = "rimuovi una squadra")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSquadra(@PathVariable @ApiParam(value = "id squadra", required = true) @Min(0) @Max(9999) Integer id) {
//        squadraService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

}
