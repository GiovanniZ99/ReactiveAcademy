package it.reactive.academy.springMvc.controller;

import it.reactive.academy.springMvc.resource.Nome;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("applicazione")
public class ApplicazioneController {

    @GetMapping
    public ResponseEntity<Nome> nome(){
        return ResponseEntity.ok().body(new Nome("Giovanni Zaffinelli"));
    }

}
