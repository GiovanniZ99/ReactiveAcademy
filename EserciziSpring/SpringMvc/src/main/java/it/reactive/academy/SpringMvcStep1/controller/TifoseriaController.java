package it.reactive.academy.SpringMvcStep1.controller;

import io.swagger.annotations.ApiParam;
import it.reactive.academy.SpringMvcStep1.dto.TifoseriaDTO;
import it.reactive.academy.SpringMvcStep1.resource.Tifoseria;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(value="tifoseria", produces = {MediaType.APPLICATION_JSON_VALUE,"application/json"})
@Validated
public class TifoseriaController {

  @PostMapping("/{idSquadra}")
  public ResponseEntity<Tifoseria> salvaTifoseria(@PathVariable @ApiParam(value = "id squadra", required = true) @Min(0)@Max(10000) Long idSquadra,
                                       @Valid @RequestBody @ApiParam(value = "tifoseria")TifoseriaDTO tifoseriaDTO){
      return ResponseEntity.ok(new Tifoseria());
  }
}
