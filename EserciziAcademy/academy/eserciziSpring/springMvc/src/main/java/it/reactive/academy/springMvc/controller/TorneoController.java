package it.reactive.academy.springMvc.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.academy.springMvc.dto.TorneoDTO;
import it.reactive.academy.springMvc.exception.ErrorResponse;
import it.reactive.academy.springMvc.resource.Torneo;
import it.reactive.academy.springMvc.service.SquadraTorneoService;
import it.reactive.academy.springMvc.service.TorneoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "tornei", produces = {MediaType.APPLICATION_JSON_VALUE, "application/json"})
@Validated
public class TorneoController {
    private final TorneoService torneoService;
    private final SquadraTorneoService squadraTorneoService;
    public TorneoController(TorneoService torneoService, SquadraTorneoService squadraTorneoService) {
        this.torneoService = torneoService;
        this.squadraTorneoService = squadraTorneoService;
    }

    @ApiOperation(value = "salva nuovo Torneo", response = Torneo.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Torneo inserito"), @ApiResponse(code = 550, message = "C6 errore di validazione", response = ErrorResponse.class)})
    @PostMapping
    public ResponseEntity<Torneo> salvaTorneo(@Valid @RequestBody @ApiParam(value = "TorneoDTO", required = true) TorneoDTO torneoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoService.create(torneoDTO));
    }

    @ApiOperation(value = "Inserisce una squadra nel torneo", response = Torneo.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success | OK"), @ApiResponse(code = 550, message = "C2 Torneo non trovato\n" + "C4 Squadra non presente\n" + "C6 Errore di validazione", response = ErrorResponse.class)})
    @PutMapping("/addSquadraToTorneo/{idTorneo}/{idSquadra}")
    public ResponseEntity<Torneo> iscrizioneSquadraAlTorneo(@PathVariable @ApiParam(value = "id torneo", required = true) @Min(0) @Max(9999) Integer idTorneo, @PathVariable @ApiParam(value = "id squadra", required = true) @Min(0) @Max(9999) Integer idSquadra) {
        return ResponseEntity.ok(squadraTorneoService.create(idTorneo, idSquadra));
    }

    @ApiOperation(value = "restituisci tutti i tornei", response = Torneo.class)
    @GetMapping
    public ResponseEntity<List<Torneo>> find() {
        return ResponseEntity.ok(squadraTorneoService.readAll());
    }

    @ApiOperation(value = "rimuovi un torneo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTorneo(@PathVariable @ApiParam(value = "id torneo", required = true) @Min(0) @Max(9999) Integer id) {
        torneoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
