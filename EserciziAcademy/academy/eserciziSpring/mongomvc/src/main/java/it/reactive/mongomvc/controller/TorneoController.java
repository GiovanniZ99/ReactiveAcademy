package it.reactive.mongomvc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.reactive.mongomvc.dto.TorneoDTO;
import it.reactive.mongomvc.exception.ErrorResponse;
import it.reactive.mongomvc.resource.TorneoResource;
import it.reactive.mongomvc.service.TorneoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/tornei")
@Validated
public class TorneoController {

    private final TorneoService torneoService;

    public TorneoController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @Operation(summary = "Salva un nuovo torneo", description = "Crea un nuovo torneo e restituisce il torneo appena creato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torneo inserito con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TorneoResource.class))),
            @ApiResponse(responseCode = "550", description = "Errore di validazione", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<TorneoResource> salvaTorneo(
            @Valid @RequestBody
            @Parameter(description = "Dati del torneo da inserire", required = true) TorneoDTO torneoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoService.create(torneoDTO));
    }

    @Operation(summary = "Aggiungi una squadra a un torneo", description = "Aggiunge una squadra al torneo specificato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Squadra aggiunta con successo al torneo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TorneoResource.class))),
            @ApiResponse(responseCode = "550", description = "Errore durante l'iscrizione della squadra al torneo, per esempio: torneo o squadra non trovati", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/addSquadraToTorneo/{idTorneo}/{idSquadra}")
    public ResponseEntity<TorneoResource> iscrizioneSquadraAlTorneo(
            @PathVariable
            @Parameter(description = "ID del torneo", required = true) @Min(0) @Max(9999) String idTorneo,
            @PathVariable
            @Parameter(description = "ID della squadra", required = true) @Min(0) @Max(9999) String idSquadra) {
        return ResponseEntity.ok(torneoService.censisciSquadraAlTorneo(idTorneo, idSquadra));
    }

    @Operation(summary = "Restituisce tutti i tornei", description = "Recupera la lista di tutti i tornei presenti.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista di tornei restituita con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TorneoResource.class))),
            @ApiResponse(responseCode = "404", description = "Nessun torneo trovato", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<TorneoResource>> findAllTornei() {
        return ResponseEntity.ok(torneoService.getTorneiCompleti());
    }

    @Operation(summary = "Rimuove un torneo", description = "Rimuove il torneo specificato in base al suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Torneo rimosso con successo"),
            @ApiResponse(responseCode = "404", description = "Torneo non trovato", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTorneo(
            @PathVariable
            @Parameter(description = "ID del torneo da rimuovere", required = true) @Min(0) @Max(9999) Integer id) {
        torneoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

