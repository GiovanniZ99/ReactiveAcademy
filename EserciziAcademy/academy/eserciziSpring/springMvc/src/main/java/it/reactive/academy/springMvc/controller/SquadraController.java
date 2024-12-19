package it.reactive.academy.springMvc.controller;

import io.swagger.annotations.*;
import it.reactive.academy.springMvc.dto.GiocatoreDTO;
import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.dto.SquadraDiGiocatoriDTO;
import it.reactive.academy.springMvc.dto.TifoseriaDTO;
import it.reactive.academy.springMvc.exception.ErrorResponse;
import it.reactive.academy.springMvc.resource.Squadra;
import it.reactive.academy.springMvc.resource.Tifoseria;
import it.reactive.academy.springMvc.service.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@RequestMapping(value = "squadre", produces = {MediaType.APPLICATION_JSON_VALUE, "application/json"})
@Validated
public class SquadraController {
    @Autowired
    SquadraService squadraService;


    @ApiOperation(value = "inserimento nuova squadra", response = Squadra.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Squadra inserita!"), @ApiResponse(code = 550, message = "C1 in caso di squadra già censita \n" + "C6 in caso di errore di validazione ", response = ErrorResponse.class)})
    @PostMapping
    public ResponseEntity<Squadra> salvaSquadra(@Valid @RequestBody SquadraDTO squadraDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(squadraService.create(squadraDto));
    }

    @ApiOperation(value = "Inserisci una squadra e la lista di giocatori", response = Squadra.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK"), @ApiResponse(code = 550, message = "C1 Squadra già censita\n" + "C6 Errore di validazione", response = ErrorResponse.class)})
    @PostMapping("squadreGiocatori")
    public ResponseEntity<Squadra> salvaSquadraEGiocatori(@Valid @RequestBody @ApiParam(value = "squadraGiocatori") SquadraDiGiocatoriDTO squadreDiGiocatoriDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Squadra());
    }

    @ApiOperation(value = "restituisce la lista di squadre, con la lista completa di giocatori o meno", response = Squadra.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK")})
    @GetMapping
    public ResponseEntity<List<Squadra>> getSquadre(@Valid @RequestParam @ApiParam(value = "booleano", required = true) Boolean completo) {
        return ResponseEntity.ok(squadraService.read(completo));
    }

    @ApiOperation(value = "aggiunge giocatore a squadra e restituisce la squadra aggiornata", response = Squadra.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK"), @ApiResponse(code = 550, message = "C3 Giocatore già censito\n" + "C4 Squadra non presente \n" + "C6 Errore di validazione", response = ErrorResponse.class)})
    @PutMapping("/addGiocatore/{id}")
    public ResponseEntity<Squadra> addGiocatore(@PathVariable @ApiParam(value = "id squadra", required = true) @Min(value = 0) @Max(value = 9999) Long id, @Valid @RequestBody @ApiParam(value = "giocatoreDTO") GiocatoreDTO giocatoreDTO) {

        return ResponseEntity.ok(new Squadra());
    }

    @ApiOperation(value = "Inserisce una tifoseria", response = Tifoseria.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK"), @ApiResponse(code = 550, message = "C4 Squadra non presente \n" +
            "C6 Errore di validazione", response = ErrorResponse.class)})
    @PutMapping("/addTifoseria/{id}")
    public ResponseEntity<Tifoseria> aggiornaSquadraConTifoseria(@PathVariable @ApiParam(value = "id squadra", required = true) @Min(value = 0) @Max(value = 9999) Long id,
                                                               @Valid @RequestBody TifoseriaDTO tifoseriaDTO) {
        return ResponseEntity.ok(new Tifoseria());
    }

    @ApiOperation(value = "rimuovi una squadra")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquadra(@PathVariable @ApiParam(value = "id squadra", required = true) @Min(0) @Max(9999) Long id) {
        return ResponseEntity.noContent().build();
    }

            }
