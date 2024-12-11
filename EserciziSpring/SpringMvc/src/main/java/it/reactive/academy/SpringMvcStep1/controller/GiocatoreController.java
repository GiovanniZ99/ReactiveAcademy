package it.reactive.academy.SpringMvcStep1.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.reactive.academy.SpringMvcStep1.resource.Giocatore;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="giocatori", produces = {MediaType.APPLICATION_JSON_VALUE,"application/json"})
public class GiocatoreController {

    @ApiOperation(value="Aumenta di 1 le ammonizioni", response = Giocatore.class)
    @PutMapping("/updateAmmonizioni/{id}")
    public ResponseEntity<Giocatore> aumentaAmmonizioni(@PathVariable @ApiParam(value = "id giocatore", required = true)Long id){
      return ResponseEntity.ok(null);
    }
}
