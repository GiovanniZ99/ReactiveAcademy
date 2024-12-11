package it.reactive.academy.SpringMvcStep1.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.academy.SpringMvcStep1.dto.TorneoDTO;
import it.reactive.academy.SpringMvcStep1.exception.SquadraNonTrovataException;
import it.reactive.academy.SpringMvcStep1.exception.TorneoNonTrovatoException;
import it.reactive.academy.SpringMvcStep1.resource.Torneo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "tornei")
@Validated
public class TorneoController {

    @ApiOperation(value = "salva nuovo Torneo")
    @PostMapping
    public ResponseEntity<Torneo> salvaTorneo(@Valid @RequestBody @ApiParam(value = "torneo", required = true) TorneoDTO torneoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new Torneo());
    }

    @ApiOperation(value = "Inserisce una squadra nel torneo", response = Torneo.class)
    @ApiResponses(value = {@ApiResponse(code = 591,message = "Squadra non trovata", response = SquadraNonTrovataException.class),
                            @ApiResponse(code = 592,message = "Torneo non trovato", response = TorneoNonTrovatoException.class)})
    @PutMapping("/addSquadraToTorneo/{idTorneo}/{idSquadra}")
    public ResponseEntity<Torneo> iscrizioneSquadraAlTorneo(@PathVariable @ApiParam(value = "id torneo", required = true) @Min(0) @Max(10000)Long idTorneo,
                                                            @PathVariable @ApiParam(value = "id squadra", required = true) @Size(min =0, max = 1000) Long idSquadra){

        return ResponseEntity.ok(new Torneo());
    }

    @ApiOperation(value = "restituisci tutti i tornei", response = Torneo.class)
    @GetMapping
    public ResponseEntity<List<Torneo>> find(){
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "rimuovi un torneo", response = Torneo.class)
    @DeleteMapping("/{id}")
    public void deleteTorneo(@PathVariable @ApiParam(value = "id torneo", required = true) @Min(0) @Max(10000) Long id){

    }

}
