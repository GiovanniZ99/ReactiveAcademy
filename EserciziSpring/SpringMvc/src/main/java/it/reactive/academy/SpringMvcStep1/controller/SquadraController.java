package it.reactive.academy.SpringMvcStep1.controller;

import io.swagger.annotations.*;
import it.reactive.academy.SpringMvcStep1.dto.GiocatoreDTO;
import it.reactive.academy.SpringMvcStep1.dto.SquadraDTO;
import it.reactive.academy.SpringMvcStep1.dto.SquadraDiGiocatoriDTO;
import it.reactive.academy.SpringMvcStep1.dto.TifoseriaDTO;
import it.reactive.academy.SpringMvcStep1.exception.NomeSquadraDuplicatoException;
import it.reactive.academy.SpringMvcStep1.resource.Squadra;
import it.reactive.academy.SpringMvcStep1.resource.Tifoseria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@RequestMapping(value = "squadre")//modificata la s da maiuscola a minuscola
public class SquadraController {



    @ApiOperation(value = "inserimento nuova squadra", response = Squadra.class)
    @PostMapping


    public ResponseEntity<Squadra> save(@Valid @RequestBody @ApiParam(value = "squadra", required = true)
                                            @NotNull(message = "il nome non può essere nullo")
                                            @Size(min = 3, max = 20, message = "Il nome deve essere compreso tra 3 e 20 caratteri")
                                            SquadraDTO squadraDTO, BindingResult bd ){
        if (bd.hasErrors()) {
            StringBuilder sb=new StringBuilder("errore di validazione del form:");
            List<ObjectError> allErrors = bd.getAllErrors();
            for (ObjectError objectError : allErrors) {
                sb.append("Errore --> ");
                sb.append(objectError.getDefaultMessage());
                sb.append(". ");
            }
            throw new NomeSquadraDuplicatoException(sb.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new Squadra());
    }

    @ApiOperation(value = "inserisci squadra di giocatori", response = Squadra.class)
    @ApiResponses(value = {@ApiResponse(code = 200,message = "Success | OK"),
            @ApiResponse(code = 591,message = "Nome Squadra già censito", response = Squadra.class)})
    @PostMapping("squadregiocatori")//squadregiocatori
    public ResponseEntity<Squadra> saveSquadraDiGiocatori(@Valid @RequestBody @ApiParam(value = "squadra", required = true) @NotNull SquadraDiGiocatoriDTO squadreDiGiocatoriDTO, BindingResult bd ){
        if (bd.hasErrors()) {
            StringBuilder sb=new StringBuilder("errore di validazione del form:");
            List<ObjectError> allErrors = bd.getAllErrors();
            for (ObjectError objectError : allErrors) {
                sb.append("Errore --> ");
                sb.append(objectError.getDefaultMessage());
                sb.append(". ");
            }
            throw new NomeSquadraDuplicatoException(sb.toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new Squadra());
    }

    @ApiOperation(value = "restituisce la lista di squadre, con la lista completa di giocatori o meno")
    @GetMapping
    public ResponseEntity<List<Squadra>> find(@RequestParam(required = true) @ApiParam(value = "completo") Boolean completo){
        return ResponseEntity.ok(null);
    }


    @ApiOperation(value = "aggiungi giocatore a squadra")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "Success | OK"),
            @ApiResponse(code = 591,message = "Giocatore già censito", response = Squadra.class),
            @ApiResponse(code = 592,message = "Squadra non presente", response = Squadra.class)})
    @PutMapping("/addGiocatore/{id}")
    public ResponseEntity<Squadra> aggiorna(@PathVariable @ApiParam(value = "id squadra", required = true) @Min(0) @Max(10000) Long id,
                                            @Valid @RequestBody @ApiParam(value = "giocatoreDTO",required = true) GiocatoreDTO giocatoreDTO){
        return ResponseEntity.ok(new Squadra());
    }


    @ApiOperation(value = "Inserisce una tifoseria", response = Tifoseria.class)
    @PutMapping("/addTifoseria/{id}")
    public ResponseEntity<Squadra> aggiornaTifoseria(@PathVariable @ApiParam(value = "id squadra", required = true)@Min(0) @Max(10000) Long id,
                                         @Valid @RequestBody @ApiParam(value = "tifoseria") TifoseriaDTO tifoseriaDTO){

        return ResponseEntity.ok(new Squadra());
    }

    @ApiOperation(value = "rimuovi una squadra", response = Squadra.class)
    @DeleteMapping("/{id}")
    public void deleteSquadra(@PathVariable @ApiParam(value = "id squadra", required = true)@Min(0) @Max(10000) Long id){

    }
}
